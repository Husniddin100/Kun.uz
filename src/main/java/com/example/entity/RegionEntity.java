package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "region")
public class RegionEntity extends BaseEntity2{
    @Column
    private Integer orderNumber;
    @Column
    private String name_uz;
    @Column
    private String name_ru;
    @Column
    private String name_en;
}
