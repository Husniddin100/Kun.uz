package com.example.controller;

import com.example.entity.EmailHistoryEntinty;
import com.example.enums.ProfileRole;
import com.example.service.EmailHistoryService;
import com.example.util.HttpRequestUtil;
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
@Tag(name = "EmailHistory Api list", description = "Api list for EmailHistory")
@RequestMapping("/email_history")
public class EmailHistoryController {
    @Autowired
    private EmailHistoryService emailHistoryService;

    @GetMapping("/getByEmail/{email}")
    private ResponseEntity<List<EmailHistoryEntinty>> getHistoryByPhone(@PathVariable String email) {
        List<EmailHistoryEntinty> entinties = emailHistoryService.getByEmail(email);
        log.info("get Email {}", email);
        return ResponseEntity.ok(entinties);
    }

    @GetMapping("/get/{date}")
    private ResponseEntity<List<EmailHistoryEntinty>> getEmailHistoryByDate(@PathVariable LocalDateTime date) {
        List<EmailHistoryEntinty> entinties = emailHistoryService.getEmailHistoryByDate(date);
        return ResponseEntity.ok(entinties);
    }

    @GetMapping("/adm/all")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                       HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.ROLE_ADMIN, ProfileRole.ROLE_MODERATOR);
        return ResponseEntity.ok(emailHistoryService.getAllByPagination(page, size));
    }

}
