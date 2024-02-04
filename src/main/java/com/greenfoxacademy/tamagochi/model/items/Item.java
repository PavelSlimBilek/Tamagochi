package com.greenfoxacademy.tamagochi.model.items;

import lombok.Getter;

@Getter
public abstract class Item {

    private static int ID = 1;
    private int id;

    private final String name;
    private final String imagePath;

    public Item(String name, String imagePath) {
        initId();
        this.name = name;
        this.imagePath = imagePath;
    }

    private void initId(){
        this.id = ID++;
    }

    public int getID() { return this.id; }
}