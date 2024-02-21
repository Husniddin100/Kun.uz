package com.example.service;

import com.example.dto.*;
import com.example.dto.commentDTO.CommentDTO;
import com.example.dto.commentDTO.CommentFilterDTO;
import com.example.dto.commentDTO.CommentListDTO;
import com.example.dto.commentDTO.CommentToDTO;
import com.example.entity.CommentEntity;
import com.example.exp.AppBadException;
import com.example.repository.CommentCustomRepository;
import com.example.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentCustomRepository commentCustomRepository;

    public boolean createComment(CommentDTO dto) {
        Optional<Boolean> optional = commentRepository.findByProfileIdAndArticleId(dto.getProfileId(), dto.getArticleId());

        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setArticleId(dto.getArticleId());
        entity.setReplyId(dto.getReplyId());
        entity.setProfileId(dto.getProfileId());
        entity.setCreatedDate(LocalDateTime.now());
        commentRepository.save(entity);
        return true;
    }

    public Boolean updateComment(Integer id, CommentDTO dto) {
        Optional<CommentEntity> optional = commentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("comment not found");
        }
        CommentEntity entity = optional.get();
        entity.setContent(dto.getContent());
        entity.setArticleId(dto.getArticleId());
        entity.setUpdatedDate(LocalDateTime.now());
        commentRepository.save(entity);
        return true;
    }

    public Boolean visibleUpdate(Integer id) {
        Optional<CommentEntity> optional = commentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("comment not found");
        }
        log.info("comment deleted {}",id);
        commentRepository.visibleFalse(id);
        return true;
    }

    public List<CommentListDTO> getList(String id) {
        Iterable<CommentEntity> list = commentRepository.getAll(id);
        List<CommentListDTO> dtoList = new LinkedList<>();
        for (CommentEntity commentEntity : list) {
            if (commentEntity != null) {
                dtoList.add(toListDTO(commentEntity));
            }
        }
        return dtoList;
    }

    public PageImpl getAllByPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<CommentEntity> studentPage = commentRepository.findAll(paging);

        List<CommentEntity> entityList = studentPage.getContent();
        Long totalElements = studentPage.getTotalElements();

        List<CommentToDTO> dtoList = new LinkedList<>();
        for (CommentEntity entity : entityList) {
            dtoList.add(toPageDTO(entity));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public PageImpl<CommentDTO> filter(CommentFilterDTO filter, int page, int size) {
        PaginationResultDTO<CommentEntity> paginationResult = commentCustomRepository.filter(filter, page, size);

        List<CommentDTO> dtoList = new LinkedList<>();
        for (CommentEntity entity : paginationResult.getList()) {
            dtoList.add(toDTO(entity));
        }
        Pageable paging = PageRequest.of(page - 1, size);
        return new PageImpl<>(dtoList, paging, paginationResult.getTotalSize());
    }

    public CommentToDTO toPageDTO(CommentEntity entity) {
        CommentToDTO dto = new CommentToDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdateDate(entity.getUpdatedDate());
        dto.setProfileId(entity.getProfileId());
        dto.setName(entity.getProfile().getName());
        dto.setSurname(entity.getProfile().getSurname());
        dto.setContent(entity.getContent());
        dto.setArticleId(entity.getArticleId());
        dto.setTitle(entity.getArticle().getTitle());
        dto.setReplyId(entity.getReplyId());
        return dto;
    }

    public CommentListDTO toListDTO(CommentEntity entity) {
        CommentListDTO dto = new CommentListDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setProfileId(entity.getProfileId());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setName(entity.getProfile().getName());
        dto.setSurname(entity.getProfile().getSurname());
        return dto;
    }

    public CommentDTO toDTO(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdateDate(entity.getUpdatedDate());
        dto.setProfileId(entity.getProfileId());
        dto.setContent(entity.getContent());
        dto.setArticleId(entity.getArticleId());
        dto.setReplyId(entity.getReplyId());
        return dto;
    }
}
