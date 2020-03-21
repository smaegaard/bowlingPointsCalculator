package com.github.smaegaard.bowlingPointsCalculator.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TraditionalScoreCalculatorTest {

    private final TraditionalScoreCalculator scoreCalculator = new TraditionalScoreCalculator();

    @Test
    void calculateScore_perfect_game() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(10,0,10,0,10,0,10,0,10,0,10,0,10,0,10,0,10,0,10,0,10,0,10,0) );
        List<Integer> expected = Arrays.asList(30,60,90,120,150,180,210,240,270,300);

        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_no_points() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0) );
        List<Integer> expected = Arrays.asList(0,0,0,0,0,0,0,0,0,0);

        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_none_finish_game_on_split() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(2,0,8,2) );
        List<Integer> expected = Arrays.asList(2,12);

        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_none_finish_mixed() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(10,0,7,2,4,4,6,4,4,2) );
        List<Integer> expected = Arrays.asList(19,28,36,50,56);

        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_none_finish_game_one_strike() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(10,0) );
        List<Integer> expected = Arrays.asList(10);

        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_none_finish_game_with_split_starting_with_zero() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(0,10) );
        List<Integer> expected = Arrays.asList(10);

        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_ends_on_split_in_10th_frame_and_then_strike() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(10,0,5,5,6,2,5,5,5,5,5,5,6,2,7,2,4,6,5,5,10) );
        List<Integer> expected = Arrays.asList(20,36,44,59,74,90,98,107,122,142);

        assertEquals(expected, actual);
    }

    /*
    T. ex. giver point [[3,7],[10,0],[8,2],[8,1],[10,0],[3,4],[7,0],[5,5],[3,2],[2,5]]
    summerne [20,40,58,67,84,91,98,111,116,123], hvor 123 er totalsummen .
     */
    @Test
    void calculateScore_from_skat_example() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(3,7,10,0,8,2,8,1,10,0,3,4,7,0,5,5,3,2,2,5) );
        List<Integer> expected = Arrays.asList(20,40,58,67,84,91,98,111,116,123);

        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_ends_with_strike_in_10th_frame_and_then_split_() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,10,0,5,5) );
        List<Integer> expected = Arrays.asList(4,8,12,16,20,24,28,32,36,56);

        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_mix_points() {
        List<Integer> actual = scoreCalculator.calculateScore( Arrays.asList(3,7,5,2,3,3,4,4,5,3,10,0,10,0,10,0,8,1,10,0,4,3) );
        List<Integer> expected = Arrays.asList(15,22,28,36,44,74,102,121,130,147);

        assertEquals(expected, actual);
    }
}