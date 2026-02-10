package com.example.demo.repository;

import com.example.demo.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

    @Query("select e.code from EmailEntity e where e.email = ?1 order by e.createdAt desc limit 1")
    Integer findByEmail(String email);
}
