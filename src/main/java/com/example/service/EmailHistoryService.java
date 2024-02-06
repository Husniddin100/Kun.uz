package com.example.service;

import com.example.dto.EmailHistoryDTO;
import com.example.entity.EmailHistoryEntinty;
import com.example.exp.AppBadException;
import com.example.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmailHistoryService {
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;

    public List<EmailHistoryEntinty> getByEmail(String email) {
        if (email == null) {
            throw new AppBadException("email is null");
        }
        List<EmailHistoryEntinty> list = emailHistoryRepository.findByEmail(email);
        if (list.isEmpty()) {
            throw new AppBadException("email not found");
        }
        return list;
    }


    public List<EmailHistoryEntinty> getEmailHistoryByDate(LocalDateTime date) {
        if (date == null) {
            throw new AppBadException("date is null");
        }
        List<EmailHistoryEntinty> entintyList = emailHistoryRepository.findByCreatedDate(date);
        return entintyList;
    }

    public PageImpl getAllByPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<EmailHistoryEntinty> studentPage = emailHistoryRepository.findAll(paging);

        List<EmailHistoryEntinty> entityList = studentPage.getContent();
        Long totalElements = studentPage.getTotalElements();

        List<EmailHistoryDTO> dtoList = new LinkedList<>();
        for (EmailHistoryEntinty entinty : entityList) {
            dtoList.add(toDTO(entinty));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }


    public EmailHistoryDTO toDTO(EmailHistoryEntinty entity) {
        EmailHistoryDTO dto = new EmailHistoryDTO();
        dto.setId(entity.getId());
        dto.setMassage(entity.getMassage());
        dto.setEmail(entity.getEmail());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
