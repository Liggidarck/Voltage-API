package com.george.server.voltage.voltage.controller;

import com.george.server.voltage.voltage.model.MessageResponse;
import com.george.server.voltage.voltage.model.Password;
import com.george.server.voltage.voltage.repository.PasswordRepository;
import com.george.server.voltage.voltage.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/voltage/passwords")
public class PasswordController {

    @Autowired
    private PasswordRepository passwordRepository;

    @PostMapping("/create")
    ResponseEntity<?> createPassword(@RequestBody Password password) {
        passwordRepository.save(password);
        return ResponseEntity.ok(new MessageResponse("Password successfully created!"));
    }

    @PutMapping("/update")
    ResponseEntity<?> updatePasswordById(@RequestParam(value = "id") long id,
                                         @RequestBody Password password) {
        passwordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Password with id " + id + " not found")
        );

        int codeUpdate = passwordRepository.updateUrlAndEmailAndPasswordById(
                password.getUrl(), password.getEmail(), password.getPassword(), id
        );

        return ResponseEntity.ok(
                new MessageResponse("Password successfully updated with code: " + codeUpdate)
        );
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deletePasswordById(@RequestParam(value = "id")long id) {
        passwordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Password with id " + id + " not found")
        );

        passwordRepository.deleteById(id);

        return ResponseEntity.ok(
                new MessageResponse("Password successfully deleted")
        );
    }

    @GetMapping("/get")
    List<Password> getAllPasswords() {
        return passwordRepository.findAll();
    }

}
