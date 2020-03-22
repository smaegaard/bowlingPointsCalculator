package com.github.smaegaard.bowlingPointsCalculator.exceptions;

public class RestConnectionException extends Exception {
    int status;

    public RestConnectionException(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "REST Connection issue. HTTP status code is: " + status;
    }
}
