package com.thekey.stylekeyserver.item;

public enum ItemErrorMessage {
    NOT_FOUND_ITEM("Item does not found with id: ");

    private String msg;

    ItemErrorMessage(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
