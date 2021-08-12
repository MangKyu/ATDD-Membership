package com.mang.atdd.membership.app.membership.service;

import com.mang.atdd.membership.app.enums.MembershipType;
import com.mang.atdd.membership.app.membership.dto.MembershipAddResponse;
import com.mang.atdd.membership.app.membership.dto.MembershipDetailResponse;
import com.mang.atdd.membership.app.membership.entity.Membership;
import com.mang.atdd.membership.app.membership.repository.MembershipRepository;
import com.mang.atdd.membership.exception.MembershipErrorResult;
import com.mang.atdd.membership.exception.MembershipException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipAddResponse addMembership(final String userId, final MembershipType membershipType, final Integer point) {
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

        return MembershipAddResponse.builder()
                .id(savedMembership.getId())
                .membershipType(savedMembership.getMembershipType())
                .build();
    }

    public List<MembershipDetailResponse> getMembershipList(String userId) {
        final List<Membership> membershipList = membershipRepository.findAllByUserId(userId);

        return membershipList.stream()
                .map(v -> MembershipDetailResponse.builder()
                        .id(v.getId())
                        .membershipType(v.getMembershipType())
                        .point(v.getPoint())
                        .createdAt(v.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public MembershipDetailResponse getMembership(String userId, MembershipType membershipType) {
        final Membership findResult = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (findResult == null) {
            throw new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
        }

        return MembershipDetailResponse.builder()
                .id(findResult.getId())
                .membershipType(findResult.getMembershipType())
                .point(findResult.getPoint())
                .createdAt(findResult.getCreatedAt())
                .build();
    }

    public void removeMembership(Long membershipId, String userId) {
        final Optional<Membership> optionalMembership = membershipRepository.findById(membershipId);
        final Membership membership = optionalMembership.orElseThrow(() -> new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));
        if (!membership.getUserId().equals(userId)) {
            throw new MembershipException(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
        }

        membershipRepository.deleteById(membershipId);
    }

}
