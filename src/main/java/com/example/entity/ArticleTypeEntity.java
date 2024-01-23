package com.example.entity;

import com.example.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Article_type")
public class ArticleTypeEntity extends BaseEntity2{
    @Column
    private Integer orderNumber;
    @Enumerated(EnumType.STRING)
    @Column
    private Language language;
}
