package com.thekey.stylekeyserver.exception;

public enum ErrorMessage {

    NOT_FOUND_STYLE_POINT("StylePoint not found with id: "),
    NOT_FOUND_BRAND("Brand does not found with id: "),
    NOT_FOUND_CATEGORY("Category does not found with id: "),
    NOT_FOUND_COORDINATE_LOOK("Coordinate Look does not found with id: "),
    NOT_FOUND_ITEM("Item does not found with id: ");

    private String msg;

    ErrorMessage(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
