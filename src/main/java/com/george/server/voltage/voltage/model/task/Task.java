package com.george.server.voltage.voltage.model.task;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private boolean status;
    private String dateComplete;
    private String dateCreate;
    private String noteTask;
    private int folderId;
}