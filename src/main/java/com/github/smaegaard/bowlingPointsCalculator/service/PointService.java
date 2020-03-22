package com.github.smaegaard.bowlingPointsCalculator.service;

import com.github.smaegaard.bowlingPointsCalculator.model.APIPointsAndToken;
import com.github.smaegaard.bowlingPointsCalculator.model.APIPostResult;
import com.github.smaegaard.bowlingPointsCalculator.model.PointsAndToken;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PointService {
    private static final int CONNECTION_TIMEOUT = 5000;
    private static PointService pointService = null;
    URL url = null;
    HttpURLConnection getConnection;
    HttpURLConnection postConnection;
    String fullURL = "http://13.74.31.101/api/points";
    Gson gson;

    private PointService() {
        gson = new Gson();
    }

    public static PointService getInstance() {
        if (pointService == null)
            pointService = new PointService();

        return pointService;
    }

    public PointsAndToken getPointsAndToken() {
        StringBuffer content = null;

        try {
            content = getJSONfromAPI();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseAPIgetInput(content);
    }

    private StringBuffer getJSONfromAPI() throws IOException {
        url = new URL(fullURL);
        getConnection = (HttpURLConnection) url.openConnection();
        getConnection.setRequestProperty("Content-Type", "application/json");
        getConnection.setRequestMethod("GET");
        getConnection.setConnectTimeout(CONNECTION_TIMEOUT);
        getConnection.setReadTimeout(CONNECTION_TIMEOUT);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = bufferedReader.readLine()) != null) {
            content.append(inputLine);
        }
        bufferedReader.close();
        getConnection.disconnect();

        return content;
    }

    private PointsAndToken parseAPIgetInput(StringBuffer content) {
        APIPointsAndToken apiPointsAndToken = gson.fromJson(String.valueOf(content), APIPointsAndToken.class);
        PointsAndToken pointsAndToken = new PointsAndToken();

        pointsAndToken.setToken(apiPointsAndToken.getToken());

        for (List<Integer> i : apiPointsAndToken.getPoints()) {
            pointsAndToken.addPoint(i.get(0));
            pointsAndToken.addPoint(i.get(1));
        }

        return pointsAndToken;
    }

    /*
    Returnerer HTTP status kode ”200 OK” hvis token er korrekt og JSON { "success": true } hvis summerne er korrekt.
     */
    public boolean validateResult(String token, List<Integer> result) throws IOException {
        APIPostResult data = new APIPostResult(token, result);
        String jsonData = gson.toJson(data);

        this.url = new URL(fullURL);
        postConnection = (HttpURLConnection) url.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        postConnection.setRequestProperty("Accept", "application/json");
        postConnection.setDoOutput(true);

        OutputStream outputStream = postConnection.getOutputStream();
        byte[] input = jsonData.getBytes("utf-8");
        outputStream.write(input, 0, input.length);

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(postConnection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            response.append(responseLine.trim());
        }

        data = gson.fromJson(response.toString(), APIPostResult.class);

        return data.isSuccess();
    }
}
