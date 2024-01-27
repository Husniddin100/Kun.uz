package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.rmi.server.UID;
import java.time.LocalDateTime;
@Setter
@Getter
public class AttachDTO {
     private UID id;
     private String original_name;
     private String path;
     private Long size;
     private Long extension;
     private LocalDateTime created_date;
}
