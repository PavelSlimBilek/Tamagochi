package com.greenfoxacademy.tamagochi.model.items;

import com.greenfoxacademy.tamagochi.model.Washing;

public abstract class Cleanser extends Item implements Washing {

    private final int effectivity;
    private final CleanserType type;


    public Cleanser(String name, String imagePath, CleanserType type, int effectivity) {
        super(name, imagePath);
        this.effectivity = effectivity;
        this.type = type;
    }

    @Override
    public int wash() {
        return this.effectivity;
    }
}