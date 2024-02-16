package com.example.dto.commentDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentFilterDTO {
    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Integer profileId;
    private String content;
    private String articleId;
    private Integer replyId;
    private Boolean visible;
}
