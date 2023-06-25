package com.george.server.voltage.voltage.repository;

import com.george.server.voltage.voltage.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
    @Transactional
    @Modifying
    @Query("update Password p set p.url = ?1, p.email = ?2, p.password = ?3 where p.id = ?4")
    int updateUrlAndEmailAndPasswordById(String url, String email, String password, Long id);
}