package com.example.repository;
import com.example.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer>, PagingAndSortingRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmail(String email);
    Optional<ProfileEntity> findByEmailAndPassword(String email,String password);
    @Query("update ProfileEntity set visible=FALSE where id=?1")
    void deleteById(Integer id);
}
