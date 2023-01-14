package com.george.server.voltage.voltage.controller.task;

import com.george.server.voltage.voltage.model.MessageResponse;
import com.george.server.voltage.voltage.model.task.Task;
import com.george.server.voltage.voltage.repository.task.TaskRepository;
import com.george.server.voltage.voltage.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voltage/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/create")
    public ResponseEntity<?> saveTask(@RequestBody Task task) {
        taskRepository.save(task);
        return ResponseEntity.ok(new MessageResponse("Task successfully created!"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTaskById(@RequestParam(value = "id") long id,
                                            @RequestBody Task task) {

        taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + id + " not found")
        );

        int codeUpdate = taskRepository.updateTitleAndStatusAndDateCompleteAndDateCreateAndNoteTaskById(
                task.getTitle(), task.isStatus(), task.getDateComplete(),
                task.getDateCreate(), task.getNoteTask(), id
        );

        return ResponseEntity.ok(
                new MessageResponse("Task successfully updated with code: " + codeUpdate)
        );

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTaskById(@RequestParam(value = "id") long id) {
        taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + id + " not found")
        );

        taskRepository.deleteById(id);

        return ResponseEntity.ok(
                new MessageResponse("Task successfully deleted")
        );
    }


    @GetMapping("/get")
    public List<Task> getTaskByFolderId(@RequestParam(value = "id") int id) {
        return taskRepository.getByFolderIdOrderByStatusAsc(id);
    }

}
