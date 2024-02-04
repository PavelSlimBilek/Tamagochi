package com.greenfoxacademy.tamagochi.model.items;

import com.greenfoxacademy.tamagochi.model.Entertaining;

public abstract class Toy extends Item implements Entertaining {

    private final int entertainmentLevel;
    private final ToyType type;

    public Toy(String name, String imagePath, ToyType type, int entertainmentLevel) {
        super(name, imagePath);
        this.entertainmentLevel = entertainmentLevel;
        this.type = type;
    }

    @Override
    public int entertain() {
        return this.entertainmentLevel;
    }
}