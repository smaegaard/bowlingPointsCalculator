package com.github.smaegaard.bowlingPointsCalculator.service;

import com.github.smaegaard.bowlingPointsCalculator.model.RESTResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RESTClient {
    private static final int CONNECTION_TIMEOUT = 5000;
    private URL url = null;
    private HttpURLConnection connection;

    public RESTResponse get(String endpoint) throws IOException {
        connection = getConnection(endpoint, requestType.GET);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = bufferedReader.readLine()) != null) {
            content.append(inputLine);
        }
        bufferedReader.close();
        connection.disconnect();

        return new RESTResponse(connection.getResponseCode(), content.toString());
    }

    public RESTResponse post(String endpoint, String body) throws IOException {
        connection = getConnection(endpoint, requestType.POST);

        OutputStream outputStream = connection.getOutputStream();
        byte[] input = body.getBytes("utf-8");
        outputStream.write(input, 0, input.length);

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            response.append(responseLine.trim());
        }
        bufferedReader.close();
        connection.disconnect();

        return new RESTResponse(connection.getResponseCode(), response.toString());
    }

    private HttpURLConnection getConnection(String endpoint, requestType type) throws IOException {
        this.url = new URL(endpoint);
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(CONNECTION_TIMEOUT);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");

        if (type == requestType.POST) {
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
        }
        if (type == requestType.GET) {
            connection.setRequestMethod("GET");
        }

        return connection;
    }

    enum requestType {POST, GET}
}
