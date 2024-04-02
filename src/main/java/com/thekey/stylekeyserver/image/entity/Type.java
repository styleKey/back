package com.thekey.stylekeyserver.image.entity;

public enum Type {
    BRAND("brand"),
    COORDINATE_LOOK("coordinateLook"),
    ITEM("item");

    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}