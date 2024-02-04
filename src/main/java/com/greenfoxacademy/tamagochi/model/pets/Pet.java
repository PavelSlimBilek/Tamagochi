package com.greenfoxacademy.tamagochi.model.pets;

import com.greenfoxacademy.tamagochi.model.*;
import com.greenfoxacademy.tamagochi.model.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public abstract class Pet implements PetActions {

    private static final int WEARINESS = 1;                                 // for each play()
    private static final int STARVATION = 2;                                // for each play()
    private static final int STAINING = new Random().nextInt(3) + 1; // for each play()
    private static final int UNHAPPINESS = 4;                               // for each clean()
    public static final int SLEEP_THRESHOLD = 4;                            // tiredness threshold for sleeping

    private static int ID = 1;
    private int daysAlive;

    private int id;
    private String name;
    private int hunger;
    private int happiness;
    private int dirtiness;
    private int tiredness;
    private String image;
    private PetType type;
    private int maxHappiness;
    private int maxHunger;
    private int maxTiredness;
    private int maxDirtiness;
    private String description;


    public Pet(String name,
               int maxHunger,
               int maxHappiness,
               int maxTiredness,
               int maxDirtiness,
               int hunger,
               int happiness,
               int tiredness,
               int dirtiness,
               String image,
               PetType type,
               String description) {
        initId();
        this.daysAlive = 0;
        this.name = name;
        this.maxHappiness = maxHappiness;
        this.maxHunger = maxHunger;
        this.maxTiredness = maxTiredness;
        this.maxDirtiness = maxDirtiness;
        this.hunger = hunger;
        this.happiness = happiness;
        this.dirtiness = dirtiness;
        this.tiredness = tiredness;
        this.image = image;
        this.type = type;
        this.description = description;
    }

    private void initId(){
        this.id = ID++;
    }

    @Override
    public boolean eat(Edible edible) {
        this.hunger -= edible.disposeFood();
        this.dirtiness += STAINING;
        this.happiness++;
        correctStats();
        return true;
    }

    @Override
    public boolean clean(Washing washing) {
        this.dirtiness -= washing.wash();
        this.happiness -= UNHAPPINESS;
        correctStats();
        return true;
    }

    @Override
    public void sleep() {
        if (this.tiredness < SLEEP_THRESHOLD) { return; }
        this.tiredness -= new Random().nextInt(3) + 1;
        this.hunger += STARVATION;
        this.happiness++;
        this.dirtiness++;
        correctStats();
        if (this.isAlive()) {
            this.daysAlive++;
        }
    }

    @Override
    public boolean play(Entertaining entertaining) {
        if(this.dirtiness == this.maxDirtiness || this.dirtiness == this.maxDirtiness - 1) { return false; }
        this.happiness += entertaining.entertain();
        this.tiredness += WEARINESS;
        this.hunger += STARVATION;
        this.dirtiness += STAINING;
        correctStats();
        return true;
    }

    public boolean use(Item item) {
        if (item instanceof Edible) {
            return this.eat((Edible) item);
        }
        if(item instanceof Washing){
            return this.clean((Washing) item);
        }
        if(item instanceof Entertaining){
            return this.play((Entertaining) item);
        }
        return false;
    }

    private void correctStats() {
        if (this.hunger > this.maxHunger) { this.hunger = this.maxHunger; }
        if (this.hunger < 0) { this.hunger = 0; }

        if (this.happiness > this.maxHappiness) { this.happiness = this.maxHappiness; }
        if (this.happiness < 0) { this.happiness = 0; }

        if (this.dirtiness > this.maxDirtiness) { this.dirtiness = this.maxDirtiness; }
        if (this.dirtiness < 0) { this.dirtiness = 0; }

        if (this.tiredness > this.maxTiredness) { this.tiredness = this.maxTiredness; }
        if (this.tiredness < 0) { this.tiredness = 0; }
    }

    public boolean isAlive() {
        return !(this.hunger == this.maxHunger && this.tiredness == this.maxTiredness);
    }
}