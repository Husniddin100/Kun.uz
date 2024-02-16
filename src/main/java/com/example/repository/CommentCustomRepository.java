package com.example.repository;

import com.example.dto.commentDTO.CommentFilterDTO;
import com.example.dto.PaginationResultDTO;
import com.example.entity.CommentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public PaginationResultDTO<CommentEntity> filter(CommentFilterDTO filter, int page, int size) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (filter.getId() != null) {
            builder.append(" and id=:id ");
            params.put(" id ", filter.getId());
        }
        if (filter.getCreatedDate() != null) {
            builder.append(" and created_date=:created_date ");
            params.put(" created_date ", filter.getCreatedDate());
        }
        if (filter.getProfileId() != null) {
            builder.append(" and profileId=:profileId ");
            params.put("profileId", filter.getProfileId());
        }
        if (filter.getArticleId() != null) {
            builder.append(" and articleId=:articleId ");
            params.put("articleId", filter.getArticleId());
        }

        StringBuilder stringBuilder = new StringBuilder("from comment where 1=1 ");
        stringBuilder.append(builder);
        stringBuilder.append(" order by createdDate desc ");

        StringBuilder countBuilder = new StringBuilder(" select count(s) from comment s where 1=1 ");
        countBuilder.append(builder);

        Query selectQuery = entityManager.createQuery(stringBuilder.toString());
        selectQuery.setMaxResults(size);
        selectQuery.setFirstResult((page - 1) * size);
        Query countQuery = entityManager.createQuery(countBuilder.toString());

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }


        List<CommentEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();

        return new PaginationResultDTO<CommentEntity>(totalElements, entityList);
    }
}
