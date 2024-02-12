package com.example.entity;

import com.example.enums.LikeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment_like")
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(updatable = false, insertable = false)
    @ManyToOne
    private ProfileEntity profile;
    @Column(name = "comment_id")
    private Integer commentId;
    @JoinColumn(updatable = false, insertable = false)
    @ManyToOne
    private CommentEntity comment;
    @Column
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    @Column
    private LikeStatus status;
}
