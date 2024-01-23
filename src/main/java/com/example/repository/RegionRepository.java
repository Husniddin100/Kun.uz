package com.example.repository;

import com.example.entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer> {
}
