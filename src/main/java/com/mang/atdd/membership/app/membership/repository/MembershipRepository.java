package com.mang.atdd.membership.app.membership.repository;

import com.mang.atdd.membership.app.enums.MembershipType;
import com.mang.atdd.membership.app.membership.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Membership findByUserIdAndMembershipType(final String userId, final MembershipType membershipType);

}
