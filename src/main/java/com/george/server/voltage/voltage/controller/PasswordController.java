package com.george.server.voltage.voltage.controller;

import com.george.server.voltage.voltage.model.MessageResponse;
import com.george.server.voltage.voltage.model.Password;
import com.george.server.voltage.voltage.repository.PasswordRepository;
import com.george.server.voltage.voltage.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/passwords")
public class PasswordController {

    @Autowired
    private PasswordRepository passwordRepository;

    @PostMapping("/create")
    ResponseEntity<?> createPassword(@RequestBody Password password) {
        Password _password = passwordRepository.save(password);
        return new ResponseEntity<>(_password, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    ResponseEntity<?> updatePasswordById(@RequestParam(value = "id") long id,
                                         @RequestBody Password password) {
        passwordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Password with id " + id + " not found")
        );

        int code = passwordRepository.updateUrlAndEmailAndPasswordById(
                password.getUrl(), password.getEmail(), password.getPassword(), id
        );

        return new ResponseEntity<>(new MessageResponse("Password update with code: " + code), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deletePasswordById(@RequestParam(value = "id")long id) {
        passwordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Password with id " + id + " not found")
        );

        passwordRepository.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("Password successfully deleted"), HttpStatus.OK);
    }

    @GetMapping("/get")
    List<Password> getAllPasswords() {
        return passwordRepository.findAll();
    }

}
