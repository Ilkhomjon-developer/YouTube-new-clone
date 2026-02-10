package com.example.demo.entity;

import com.example.demo.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "channel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelEntity extends BaseEntity {

    @Column(name = "owner_id")
    private Long ownerId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private UserEntity owner;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 5000)
    private String description;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "banner_image_url")
    private String bannerImageUrl;

    @Column(name = "custom_url", unique = true, length = 50)
    private String customUrl;  // e.g. @mychannel or slug for /c/mychannel

    @Column(name = "subscriber_count", nullable = false)
    @Builder.Default
    private Long subscriberCount = 0L;

    @Column(name = "is_verified", nullable = false)
    @Builder.Default
    private Boolean isVerified = false;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
}
