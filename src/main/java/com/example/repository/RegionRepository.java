package com.example.repository;

import com.example.entity.RegionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer> {
    @Query("update RegionEntity set visible=FALSE where id=?1")
    Optional<RegionEntity> delete(Integer id);
}
