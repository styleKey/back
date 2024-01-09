package com.thekey.stylekeyserver.brand;

public enum BrandErrorMessage {
    NOT_FOUND_BRAND("Brand does not found with id: ");

    private String msg;

    BrandErrorMessage(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
