package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "article_types")
public class ArticleTypesEntity extends BaseEntity {

    @Column(name = "article_id")
    private String articleId;
    @ManyToOne
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    private ArticleEntity article;

    @Column(name = "types_id")
    private Integer typesId;
    @ManyToOne
    @JoinColumn(name = "types_id", insertable = false, updatable = false)
    private TypeEntity types;
}
