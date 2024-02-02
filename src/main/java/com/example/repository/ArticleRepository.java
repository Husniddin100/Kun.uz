package com.example.repository;

import com.example.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleEntity,Integer> {

}
