package com.george.server.voltage.voltage.repository;

import com.george.server.voltage.voltage.model.task.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
}