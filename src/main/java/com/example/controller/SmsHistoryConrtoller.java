package com.example.controller;

import com.example.entity.SmsHistoryEntity;
import com.example.enums.ProfileRole;
import com.example.service.SmsHistoryService;
import com.example.util.HttpRequestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@Tag(name = "Api for SmsHistory", description = "this api used SmsHistory")
@RequestMapping("sms_history")
public class SmsHistoryConrtoller {
    @Autowired
    private SmsHistoryService smsHistoryService;

    @Operation(summary = "Api for get phone", description = "this api used for smsHistory for list")
    @GetMapping("/getByPhone/{phone}")
    public ResponseEntity<List<SmsHistoryEntity>> getByPhone(@PathVariable String phone) {
        List<SmsHistoryEntity> list = smsHistoryService.getByPhone(phone);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<SmsHistoryEntity>> getByDate(@PathVariable LocalDateTime date) {
        List<SmsHistoryEntity> list = smsHistoryService.getByDate(date);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/adm/all")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(smsHistoryService.getAllByPagination(page, size));
    }
}
