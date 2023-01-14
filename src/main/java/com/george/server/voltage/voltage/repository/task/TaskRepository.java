package com.george.server.voltage.voltage.repository.task;

import com.george.server.voltage.voltage.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Transactional
    @Modifying
    @Query("""
            update Task t set t.title = ?1, t.status = ?2, t.dateComplete = ?3, t.dateCreate = ?4, t.noteTask = ?5
            where t.id = ?6""")
    int updateTitleAndStatusAndDateCompleteAndDateCreateAndNoteTaskById(String title, boolean status, String dateComplete, String dateCreate, String noteTask, Long id);

    List<Task> getByFolderIdOrderByStatusAsc(int folderId);

    @Transactional
    @Modifying
    @Query("delete from Task t where t.folderId = ?1")
    int deleteByFolderId(long folderId);
}