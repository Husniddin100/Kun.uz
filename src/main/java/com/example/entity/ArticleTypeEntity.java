package com.example.entity;

import com.example.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ArticleType")
public class ArticleTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer orderNumber;
    @Column
    private Language language;
    @Column
    private Boolean visible;
    @Column
    private LocalDateTime createdDate;

}
