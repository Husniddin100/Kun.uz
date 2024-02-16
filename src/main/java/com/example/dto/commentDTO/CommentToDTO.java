package com.example.dto.commentDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentToDTO {
    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Integer profileId;
    private String name;
    private String surname;
    private String content;
    private String articleId;
    private String title;
    private Integer replyId;
}
