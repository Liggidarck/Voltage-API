package com.george.server.voltage.voltage.controller.task;

import com.george.server.voltage.voltage.model.MessageResponse;
import com.george.server.voltage.voltage.model.task.Folder;
import com.george.server.voltage.voltage.repository.task.FolderRepository;
import com.george.server.voltage.voltage.repository.task.TaskRepository;
import com.george.server.voltage.voltage.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voltage/folders")
public class FolderController {

    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/create")
    public ResponseEntity<?> saveFolder(@RequestBody Folder folder) {
        folderRepository.save(folder);
        return ResponseEntity.ok(new MessageResponse("Folder successfully created!"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateFolder(@RequestParam(value = "id") long id,
                                          @RequestBody Folder folder) {
        folderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Folder with id " + id + " not found")
        );

        int codeUpdate = folderRepository.updateNameFolderById(folder.getNameFolder(), id);


        return ResponseEntity.ok(
                new MessageResponse("Folder successfully updated with code: " + codeUpdate)
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFolder(@RequestParam(value = "id") long id) {
        folderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Folder with id " + id + " not found")
        );

        taskRepository.deleteByFolderId(id);

        return ResponseEntity.ok(
                new MessageResponse("Folder successfully deleted")
        );
    }

    @GetMapping("/getAllFolders")
    public List<Folder> getAll() {
        return folderRepository.findAll();
    }

}
