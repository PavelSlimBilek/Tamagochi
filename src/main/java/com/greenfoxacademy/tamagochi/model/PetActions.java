package com.greenfoxacademy.tamagochi.model;

public interface PetActions {

    boolean eat(Edible edible);
    boolean play(Entertaining entertaining);
    void sleep();
    boolean clean(Washing washing);
}