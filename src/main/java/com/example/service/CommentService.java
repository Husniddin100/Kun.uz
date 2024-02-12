package com.example.service;

import com.example.dto.CommentDTO;
import com.example.dto.CommentListDTO;
import com.example.entity.CommentEntity;
import com.example.exp.AppBadException;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public boolean createComment(CommentDTO dto) {
        Optional<Boolean> optional = commentRepository.findByProfileIdAndArticleId(dto.getProfileId(), dto.getArticleId());
        if (optional.isPresent()) {
            throw new AppBadException("you have already clicked like");
        }
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
        entity.setProfileId(dto.getProfileId());
        entity.setCreatedDate(LocalDateTime.now());
        commentRepository.save(entity);
        return true;
    }

    public Boolean deleteComment(Integer id) {
        Optional<CommentEntity> optional = commentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("comment not found");
        }
        commentRepository.visibleFalse(id);
        return true;
    }

    public List<CommentListDTO> getList(String id) {
        Iterable<CommentEntity> list = commentRepository.getAll(id);
        List<CommentListDTO> dtoList = new LinkedList<>();
        for (CommentEntity commentEntity : list) {
            if (commentEntity != null && commentEntity.getVisible().equals(Boolean.TRUE)) {
                dtoList.add(toDTO(commentEntity));
            }
        }
        return dtoList;
    }


    public CommentListDTO toDTO(CommentEntity entity) {
        CommentListDTO dto = new CommentListDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setProfileId(entity.getProfileId());
        dto.setName(entity.getProfile().getName());
        dto.setSurname(entity.getProfile().getSurname());
        return dto;
    }
}
