package com.example.entity;

import com.example.enums.LikeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article_like")
public class ArticleLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    @OneToOne
    private ProfileEntity profile;
    @Column(name = "article_id")
    private String articleId;
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    @OneToOne
    private ArticleEntity article;
    @Column
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    @Column
    private LikeStatus status;

}
