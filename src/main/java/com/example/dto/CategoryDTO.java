package com.example.dto;

import com.example.enums.Language;

import java.time.LocalDateTime;

public class CategoryDTO {
    private Integer id;
    private Integer order_number;
    private Language language;
    private Boolean visible;
    private LocalDateTime createdDate;
}
