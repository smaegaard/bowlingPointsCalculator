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
        bowlingGame = new BowlingGame(pointsAndToken, new TraditionalScoreCalculator());

        boolean result = false;
        try {
            result = PointService.getInstance().validateResult(bowlingGame.getToken(), bowlingGame.getResult());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("points: " + pointsAndToken.getPoints().toString() );
        System.out.println("result: " + bowlingGame.getResult().toString() );

        if (result) {
            System.out.println("The points were correctly summed and verified.");
        } else {
            System.out.println("The points were not correctly summed and failed verification.");
        }
    }
}
