package com.george.server.voltage.voltage.repository;

import com.george.server.voltage.voltage.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
}