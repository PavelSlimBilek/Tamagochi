package com.greenfoxacademy.tamagochi.repository;

import com.greenfoxacademy.tamagochi.model.items.*;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Repository
public class ItemRepo {

    private final List<Item> items;

    public ItemRepo() {
        this.items = new ArrayList<>(16);
        items.add(new Sponge());
        items.add(new Bone());
        items.add(new Brush());
        items.add(new Sausage());
        items.add(new Ball());
        items.add(new SoftToy());
        items.add(new Apple());
        items.add(new Meat());
        items.add(new Meat());
        items.add(new Sausage());
        items.add(new Brush());
        items.add(new SoftToy());
        items.add(new Sponge());
        items.add(new Apple());
        items.add(new Sausage());
        items.add(new Ball());
    }

    public Optional<Item> getItem(int itemID){
        return items.stream()
                .filter(i -> i.getID() == itemID)
                .findFirst();
    }
}