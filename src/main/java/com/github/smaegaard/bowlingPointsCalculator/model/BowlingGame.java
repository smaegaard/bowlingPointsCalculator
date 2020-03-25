package com.github.smaegaard.bowlingPointsCalculator.model;

import java.util.List;

public class BowlingGame {
    PointsAndToken pointsAndToken;
    IScoreCalculator scoreCalculator;

    public BowlingGame(PointsAndToken pointsAndToken, IScoreCalculator scoreCalculator) {
        this.pointsAndToken = pointsAndToken;
        this.scoreCalculator = scoreCalculator;
    }

    public String getToken() {
        return pointsAndToken.getToken();
    }

    public List<Integer> getPoints() {
        return this.pointsAndToken.getPoints();
    }

    public List<Integer> getResult() {
        return scoreCalculator.calculateScore(this.pointsAndToken.getPoints());
    }
}
