package com.mang.atdd.membership.app.membership.entity;

import com.mang.atdd.membership.app.enums.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table
@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private final Long id;

    @Enumerated(EnumType.STRING)
    private final MembershipType membershipType;

    @Column(nullable = false)
    private final String userId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private final Integer point;

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private final LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(length = 20)
    private final LocalDateTime updatedAt;

}
