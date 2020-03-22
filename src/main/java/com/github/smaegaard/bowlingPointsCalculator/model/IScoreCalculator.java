package com.github.smaegaard.bowlingPointsCalculator.model;

import java.util.List;

/*
    Points are in a 1 dimensional list, but 2 numbers are pair together.
    ex. the list 3,4,3,7,10,0  is 3 frames [3,4],[3,7],[10,0]
 */
public interface IScoreCalculator {
    List<Integer> calculateScore(List<Integer> points);
}
