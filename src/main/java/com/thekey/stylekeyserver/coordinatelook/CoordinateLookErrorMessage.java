package com.thekey.stylekeyserver.coordinatelook;

public enum CoordinateLookErrorMessage {
    NOT_FOUND_COORDINATE_LOOK("Coordinate Look does not found with id: ");

    private String msg;

    CoordinateLookErrorMessage(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
