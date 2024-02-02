package com.example.service;

import com.example.dto.EmailHistoryDTO;
import com.example.dto.SmsHistoryDTO;
import com.example.entity.EmailHistoryEntinty;
import com.example.repository.SmsHistoryRepository;
import com.example.entity.SmsHistoryEntity;
import com.example.exp.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class SmsHistoryService {
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    public List<SmsHistoryEntity> getByPhone(String phone) {
        if (phone == null) {
            throw new AppBadException("phone is null");
        }
        List<SmsHistoryEntity> list = smsHistoryRepository.findByPhone(phone);
        if (list.isEmpty()) {
            throw new AppBadException("phone not found");
        }
        return list;
    }

    public List<SmsHistoryEntity> getByDate(LocalDateTime date) {
        if (date == null) {
            throw new AppBadException("date is null");
        }
        List<SmsHistoryEntity> list = smsHistoryRepository.findByCreatedDate(date);
        if (list.isEmpty()) {
            throw new AppBadException("date not found");
        }
        return list;
    }

    public PageImpl getAllByPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<SmsHistoryEntity> studentPage = smsHistoryRepository.findAll(paging);

        List<SmsHistoryEntity> entityList = studentPage.getContent();
        Long totalElements = studentPage.getTotalElements();

        List<SmsHistoryDTO> dtoList = new LinkedList<>();
        for (SmsHistoryEntity entinty : entityList) {
            dtoList.add(toDTO(entinty));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public SmsHistoryDTO toDTO(SmsHistoryEntity entity) {
        SmsHistoryDTO dto = new SmsHistoryDTO();
        dto.setId(entity.getId());
        dto.setMassage(entity.getMassage());
        dto.setPhone(entity.getPhone());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
