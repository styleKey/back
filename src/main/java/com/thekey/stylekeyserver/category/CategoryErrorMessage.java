package com.thekey.stylekeyserver.category;

public enum CategoryErrorMessage {
    NOT_FOUND_CATEGORY("Category does not found with id: ");

    private String msg;

    CategoryErrorMessage(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
