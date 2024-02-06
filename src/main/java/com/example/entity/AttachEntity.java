package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.rmi.server.UID;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "original_name")
    private String originalName;
    @Column
    private String path;
    @Column
    private Long size;
    @Column
    private String extension;
    @Column(name = "created_date")
    private LocalDateTime createdData;
}
