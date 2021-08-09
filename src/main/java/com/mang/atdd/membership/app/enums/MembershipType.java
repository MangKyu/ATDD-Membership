package com.mang.atdd.membership.app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MembershipType {

    NAVER("네이버"),
    ;

    private final String companyName;

}
