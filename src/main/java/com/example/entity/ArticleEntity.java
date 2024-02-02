package com.example.entity;

import com.example.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity extends BaseEntity {
    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String content;
    @Column
    private Integer shared_count;
    @JoinColumn(name = "region_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private RegionEntity region;
    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;
    @Column
    private Integer m_id;
    @Column
    private Integer p_id;
    @Column
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime publishedDate;
    @Column
    private Integer view_count;
}
