package com.github.smaegaard.bowlingPointsCalculator.service;


import com.github.smaegaard.bowlingPointsCalculator.model.APIPointsAndToken;
import com.github.smaegaard.bowlingPointsCalculator.model.APIPostResult;
import com.github.smaegaard.bowlingPointsCalculator.model.PointsAndToken;
import com.google.gson.Gson;

import java.util.List;

public class JsonConverter {
    private Gson gson;

    public JsonConverter() {
        this.gson = new Gson();
    }

    public PointsAndToken getPointsAndTokenFromJson(String input) {
        APIPointsAndToken apiPointsAndToken = gson.fromJson(input, APIPointsAndToken.class);
        PointsAndToken pointsAndToken = new PointsAndToken();

        pointsAndToken.setToken(apiPointsAndToken.getToken());

        for (List<Integer> i : apiPointsAndToken.getPoints()) {
            pointsAndToken.addPoint(i.get(0));
            pointsAndToken.addPoint(i.get(1));
        }

        return pointsAndToken;
    }

    public String getJsonFromAPIPostResult(APIPostResult apiPostResult) {
        return gson.toJson(apiPostResult);
    }

    public APIPostResult getAPIPostResultFromJson(String input) {
        return gson.fromJson(input, APIPostResult.class);
    }
}
