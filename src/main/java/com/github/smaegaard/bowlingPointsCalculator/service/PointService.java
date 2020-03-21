package com.github.smaegaard.bowlingPointsCalculator.service;

import com.github.smaegaard.bowlingPointsCalculator.model.PointsAndToken;

import java.util.List;

public class PointService {
    private static PointService pointService = null;

    public static PointService getInstance() {
        if (pointService == null)
            pointService = new PointService();

        return pointService;
    }

    public PointsAndToken getPointsAndToken() {
        // TODO fix me
        return null;
    }
    /*
    Returnerer HTTP status kode ”200 OK” hvis token er korrekt og JSON { "success": true } hvis summerne er korrekt.
     */
    public boolean validateResult(String token, List<Integer> result) {
        // TODO fix me
        return false;
    }
}
