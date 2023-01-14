package com.george.server.voltage.voltage.repository;

import com.george.server.voltage.voltage.model.task.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}