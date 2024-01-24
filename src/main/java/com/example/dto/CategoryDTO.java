package com.example.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class CategoryDTO {
    private Integer id;
    private Integer order_number;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private Boolean visible;
    private LocalDateTime createdDate;
}
