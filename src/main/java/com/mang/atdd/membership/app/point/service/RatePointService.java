package com.mang.atdd.membership.app.point.service;

import org.springframework.stereotype.Service;

@Service
public class RatePointService implements PointService {

    private static final int POINT_RATE = 1;

    @Override
    public int calculateAmount(final int price) {
        return price * POINT_RATE / 100 ;
    }

}
