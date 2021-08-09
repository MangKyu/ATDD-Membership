package com.mang.atdd.membership.app.membership.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    @Column(nullable = false, length = 20)
    private final String membershipName;

}
