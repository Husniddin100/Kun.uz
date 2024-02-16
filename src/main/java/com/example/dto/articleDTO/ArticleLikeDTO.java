package com.example.dto.articleDTO;

import com.example.enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleLikeDTO {
    private Integer id;
    private Integer profileId;
    private String articleId;
    private LocalDateTime createdDate;
    private LikeStatus status;
}
