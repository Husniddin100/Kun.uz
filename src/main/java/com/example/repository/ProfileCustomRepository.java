package com.example.repository;


import com.example.dto.PaginationResultDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProfileCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public PaginationResultDTO<ProfileEntity> filter(ProfileFilterDTO filter, int page, int size) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (filter.getId() != null) {
            builder.append("and id =:id ");
            params.put("id", filter.getId());
        }
        if (filter.getName() != null) {
            builder.append("and name =:name ");
            params.put("name", filter.getName());
        }
        if (filter.getSurname() != null) {
            builder.append("and surname=:surname ");
            params.put("surname",  filter.getSurname());
        }
        if (filter.getEmail()!=null){
            builder.append(" and email=:email ");
            params.put("email",filter.getEmail());
        }
        if (filter.getPassword()!=null){
            builder.append(" and password=:password ");
            params.put("password",filter.getPassword());
        }
        if (filter.getStatus()!=null){
            builder.append(" and status=:status ");
            params.put("status",filter.getStatus());
        }
        if (filter.getRole()!=null){
            builder.append(" and role=:role ");
            params.put("role",filter.getRole());
        }
        if (filter.getVisible()!=null){
            builder.append(" and visible=:visible ");
            params.put("visible",filter.getVisible());
        }
        if (filter.getCreatedDate()!=null){
            builder.append(" and createdDate=:createdDate");
            params.put("createdDate",filter.getCreatedDate());
        }

        StringBuilder selectBuilder = new StringBuilder("FROM ProfileEntity s where 1=1 ");
        selectBuilder.append(builder);
        selectBuilder.append(" order by id desc ");

        StringBuilder countBuilder = new StringBuilder("Select count(s) FROM ProfileEntity s where 1=1 ");
        countBuilder.append(builder);

        Query selectQuery = entityManager.createQuery(selectBuilder.toString());
        selectQuery.setMaxResults(size);
        selectQuery.setFirstResult((page-1)*size);
        Query countQuery = entityManager.createQuery(countBuilder.toString());

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }
        List<ProfileEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();

        return new PaginationResultDTO<ProfileEntity>(totalElements,entityList);
    }

}


