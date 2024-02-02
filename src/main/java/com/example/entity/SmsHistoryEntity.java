package com.example.entity;

import ch.qos.logback.core.util.Loader;
import com.example.enums.SmsStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "sms_history")
public class SmsHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String phone;
    @Column(columnDefinition = "text")
    private String massage;
    @Column
    @Enumerated(EnumType.STRING)
    private SmsStatus status;
    @Column
    private LocalDateTime createdDate;
}
