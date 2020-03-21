package com.github.smaegaard.bowlingPointsCalculator;

import com.github.smaegaard.bowlingPointsCalculator.model.BowlingGame;
import com.github.smaegaard.bowlingPointsCalculator.model.IScoreCalculator;
import com.github.smaegaard.bowlingPointsCalculator.model.PointsAndToken;
import com.github.smaegaard.bowlingPointsCalculator.model.TraditionalScoreCalculator;
import com.github.smaegaard.bowlingPointsCalculator.service.PointService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BowlingPointsCalculator {
    private PointService pointService;
    private BowlingGame bowlingGame = null;
    private PointsAndToken pointsAndToken = null;

    public BowlingPointsCalculator() {
    }

    public void run() {
        pointsAndToken = PointService.getInstance().getPointsAndToken();

        //bowlingGame = new BowlingGame(pointsAndToken, new TraditionalScoreCalculator());
        bowlingGame = new BowlingGame(new PointsAndToken("token", Arrays.asList(10, 0, 5, 5, 6, 2, 5, 5, 5, 5, 5, 5, 6, 2, 7, 2, 4, 6, 5, 5, 10)), new TraditionalScoreCalculator());

        boolean result = PointService.getInstance().validateResult(bowlingGame.getToken(), bowlingGame.getResult());

        if (result) {
            System.out.println("The points were correctly summed and verified.");
        } else {
            System.out.println("The points were not correctly summed and failed verification.");
        }
    }
}
