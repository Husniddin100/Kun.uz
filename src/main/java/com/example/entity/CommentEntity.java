package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(name = "profile_id", updatable = false, insertable = false)
    @ManyToOne
    private ProfileEntity profile;
    @Column
    private String content;
    @Column(name = "article_id")
    private String articleId;
    @JoinColumn(name = "article_id", updatable = false, insertable = false)
    @ManyToOne
    private ArticleEntity article;
    /// maybe join profile
    @Column
    private Integer replyId;
}
