package com.example.dto;

import com.example.entity.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.rmi.server.UID;
import java.time.LocalDateTime;
@Getter
@Setter
public class ArticleDTO {
    private UID id;
    private String title;
    private String description;
    private String content;
    private Integer sharedCount;
    private Integer imageId;
    private Integer regionId;
    private Integer categoryId;
    private Integer moderatorId;
    private Integer publisherId;
    private ArticleStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private Boolean visible;
    private Integer viewCount;

}
