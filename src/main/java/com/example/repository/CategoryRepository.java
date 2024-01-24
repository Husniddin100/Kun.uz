package com.example.repository;

import com.example.entity.CategoryEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository  extends CrudRepository<CategoryEntity ,Integer> {
    Iterable<CategoryEntity> findAll(Sort sort);
}
