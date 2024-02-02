package com.example.dto;

import com.example.enums.SmsStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class SmsHistoryDTO {
    private Integer id;
    private String phone;
    private String massage;
    private SmsStatus status;
    private LocalDateTime createdDate;
}
