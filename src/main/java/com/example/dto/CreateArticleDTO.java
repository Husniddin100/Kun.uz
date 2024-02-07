package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateArticleDTO {
    @NotNull(message = "title is null")
    @NotEmpty(message = "title empty")
    private String title;
    @NotNull(message = "description is null")
    @NotEmpty(message = "description empty")
    private String description;
    @NotNull(message = "content is null")
    @NotEmpty(message = "content empty")
    private String content;

    private Integer regionId;
    private Integer categoryId;
    private List<Integer> articleType;
    private String photoId;
}
