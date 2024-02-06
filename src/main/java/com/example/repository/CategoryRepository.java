package com.example.repository;

import com.example.entity.CategoryEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CategoryRepository  extends CrudRepository<CategoryEntity ,Integer> {
    Iterable<CategoryEntity> findAll(Sort sort);
    @Query("update CategoryEntity set visible=FALSE where id=?1")
    Optional<CategoryEntity> delete(Integer id);

}
