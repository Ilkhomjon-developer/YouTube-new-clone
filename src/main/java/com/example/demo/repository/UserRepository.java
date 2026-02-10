package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    @Query("from UserEntity e where e.email = ?1 and e.isActive = true ")
    Optional<UserEntity> getUserStatus(String email);

    @Query("from UserEntity e where e.email = ?1 and e.isActive = true and e.status = 'REGISTERING'")
    Optional<UserEntity> findByEmailAndIsActiveTrue(String email);

    @Transactional
    @Modifying
    @Query("update UserEntity e set e.status = 'ACTIVE' where e.email = ?1 and e.status = 'REGISTERING'")
    void updateUserStatus(String email);
}
