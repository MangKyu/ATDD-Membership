package com.mang.atdd.membership.app.membership.entity;

import com.mang.atdd.membership.app.enums.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


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

}
