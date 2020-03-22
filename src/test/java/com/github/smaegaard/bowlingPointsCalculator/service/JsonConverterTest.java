package com.github.smaegaard.bowlingPointsCalculator.service;

import com.github.smaegaard.bowlingPointsCalculator.model.APIPostResult;
import com.github.smaegaard.bowlingPointsCalculator.model.PointsAndToken;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JsonConverterTest {
    private final JsonConverter jsonConverter = new JsonConverter();

    @Test
    void getPointsAndTokenFromJson() {
        String input = "{\"points\":[[1,2],[3,4],[5,6]],\"token\":\"mytoken\"}";
        PointsAndToken actual = jsonConverter.getPointsAndTokenFromJson(input);
        PointsAndToken expected = new PointsAndToken("mytoken", Arrays.asList(1, 2, 3, 4, 5, 6));

        assertEquals(expected.getToken(), actual.getToken());
        assertEquals(expected.getPoints(), actual.getPoints());
    }

    @Test
    void getJsonFromAPIPostResult() {
        String expected = "{\"token\":\"mytoken\",\"success\":false,\"points\":[7,15]}";
        String actual = jsonConverter.getJsonFromAPIPostResult(new APIPostResult("mytoken", Arrays.asList(7, 15)));

        assertEquals(expected, actual);
    }

    @Test
    void getAPIPostResultFromJson() {
        String input = "{\"success\":false,\"points\":[1,2,3,4,5,6]}";
        APIPostResult expected = new APIPostResult("mytoken", Arrays.asList(1, 2, 3, 4, 5, 6));
        APIPostResult actual = jsonConverter.getAPIPostResultFromJson(input);

        assertEquals(expected.getPoints(), actual.getPoints());
        assertEquals(expected.isSuccess(), actual.isSuccess());
    }
}