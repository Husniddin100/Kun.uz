package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.rmi.server.UID;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "original_name")
    private String original_name;
    @Column
    private String path;
    @Column
    private Long size;
    @Column
    private Long extension;
    @Column
    private LocalDateTime created_date;
}
