package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {
    private Integer id;
    @NotNull(message = "OrderNumber required")
    private Integer order_number;
    @Size(min = 10, max = 200, message = "NameUz must be Between 10 and 200 characters")
    private String name_uz;
    @NotBlank(message = "NameRu must be Between 10 and 200 characters")
    @Size(min = 10, max = 200, message = "NameRu must be Between 10 and 200 characters")
    private String name_ru;
    @NotBlank(message = "NameEn must be Between 10 and 200 characters")
    @Size(min = 10, max = 200, message = "NameEn must be Between 10 and 200 characters")
    private String name_en;

    private Boolean visible;

    private LocalDateTime createdDate;

    private String name;
}