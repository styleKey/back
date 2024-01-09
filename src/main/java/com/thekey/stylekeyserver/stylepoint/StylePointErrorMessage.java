package com.thekey.stylekeyserver.stylepoint;

public enum StylePointErrorMessage {
    NOT_FOUND_STYLE_POINT("StylePoint not found with id: ");

    private String msg;

    StylePointErrorMessage(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
