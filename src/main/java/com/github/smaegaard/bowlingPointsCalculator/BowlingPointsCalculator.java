package com.github.smaegaard.bowlingPointsCalculator;

import com.github.smaegaard.bowlingPointsCalculator.exceptions.RestConnectionException;
import com.github.smaegaard.bowlingPointsCalculator.model.BowlingGame;
import com.github.smaegaard.bowlingPointsCalculator.model.PointsAndToken;
import com.github.smaegaard.bowlingPointsCalculator.model.TraditionalScoreCalculator;
import com.github.smaegaard.bowlingPointsCalculator.service.PointService;

import java.io.IOException;

public class BowlingPointsCalculator {
    private PointService pointService;
    private BowlingGame bowlingGame = null;
    private PointsAndToken pointsAndToken = null;

    public BowlingPointsCalculator() {
    }

    public void run() {
        try {
            pointsAndToken = PointService.getInstance().getPointsAndToken();
        } catch (RestConnectionException | IOException e) {
            e.printStackTrace();
        }

        bowlingGame = new BowlingGame(pointsAndToken, new TraditionalScoreCalculator());

        boolean result = false;
        try {
            result = PointService.getInstance().postResult(bowlingGame.getToken(), bowlingGame.getResult());
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
