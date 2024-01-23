package com.example.entity;

import com.example.enums.Language;
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
    @Enumerated(EnumType.STRING)
    @Column
    private Language language;

}
