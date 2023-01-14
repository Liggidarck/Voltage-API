package com.george.server.voltage.voltage.repository.task;

import com.george.server.voltage.voltage.model.task.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Transactional
    @Modifying
    @Query("update Folder f set f.nameFolder = ?1 where f.id = ?2")
    int updateNameFolderById(String nameFolder, Long id);

}