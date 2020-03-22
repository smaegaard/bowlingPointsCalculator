package com.github.smaegaard.bowlingPointsCalculator.service;

import com.github.smaegaard.bowlingPointsCalculator.exceptions.RestConnectionException;
import com.github.smaegaard.bowlingPointsCalculator.model.APIPostResult;
import com.github.smaegaard.bowlingPointsCalculator.model.PointsAndToken;
import com.github.smaegaard.bowlingPointsCalculator.model.RESTResponse;

import java.io.*;
import java.util.List;

public class PointService {
    private static PointService pointService = null;
    private final String pointsAndTokenEndpoint = "http://13.74.31.101/api/points";
    private final String validateResultEndpoint = "http://13.74.31.101/api/points";
    private RESTClient restClient;
    private JsonConverter jsonConverter;

    private PointService() {
        restClient = new RESTClient();
        jsonConverter = new JsonConverter();
    }

    public static PointService getInstance() {
        if (pointService == null)
            pointService = new PointService();

        return pointService;
    }

    public PointsAndToken getPointsAndToken() throws RestConnectionException {
        RESTResponse restResponse = null;

        try {
            restResponse = restClient.get(pointsAndTokenEndpoint);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if( restResponse.getStatus() >= 300 ) {
            throw new RestConnectionException(restResponse.getStatus());
        }

        return jsonConverter.getPointsAndTokenFromJson(restResponse.getBody());
    }

    public boolean validateResult(String token, List<Integer> result) throws IOException {
        String jsonData = jsonConverter.getJsonFromAPIPostResult(new APIPostResult(token, result));

        RESTResponse response = restClient.post(validateResultEndpoint, jsonData);

        APIPostResult data = jsonConverter.getAPIPostResultFromJson(response.getBody());

        return data.isSuccess();
    }
}
