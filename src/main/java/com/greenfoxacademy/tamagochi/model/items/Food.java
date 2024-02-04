package com.greenfoxacademy.tamagochi.model.items;

import com.greenfoxacademy.tamagochi.model.Edible;

public abstract class Food extends Item implements Edible {

    private final int saturation;
    private final FoodType type;

    public Food(String name, String imagePath, FoodType type, int saturation) {
        super(name, imagePath);
        this.type = type;
        this.saturation = saturation;
    }

    @Override
    public int disposeFood() {
        return this.saturation;
    }
}