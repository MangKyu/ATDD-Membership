package com.mang.atdd.membership.app.membership.service;

import com.mang.atdd.membership.app.enums.MembershipType;
import com.mang.atdd.membership.app.exception.MembershipErrorResult;
import com.mang.atdd.membership.app.exception.MembershipException;
import com.mang.atdd.membership.app.membership.dto.MembershipResponse;
import com.mang.atdd.membership.app.membership.entity.Membership;
import com.mang.atdd.membership.app.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipResponse addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (result != null) {
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        final Membership membership = Membership.builder()
                .userId(userId)
                .point(point)
                .membershipType(membershipType)
                .build();

        final Membership savedMembership = membershipRepository.save(membership);

        return MembershipResponse.builder()
                .id(savedMembership.getId())
                .membershipType(savedMembership.getMembershipType())
                .build();
    }

}
