package com.example.dto;

import com.example.enums.Language;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleTypeDTO {
    private Integer id;
    private Integer order_number;
    private Language language;
    private Boolean visible;
    private LocalDateTime createdDate;
}
