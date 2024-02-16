package com.example.dto.commentDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
@Getter
@Setter
public class CommentListDTO {
    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Integer profileId;
    private String name;
    private String surname;

}
