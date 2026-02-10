package com.example.demo.repository;

import com.example.demo.entity.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {

    Optional<ChannelEntity> findByOwnerId(Long ownerId);

    boolean existsByCustomUrl(String customUrl);

    boolean existsByOwnerId(Long ownerId);
}
