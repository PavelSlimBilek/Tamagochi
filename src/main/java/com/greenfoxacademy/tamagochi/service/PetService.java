package com.greenfoxacademy.tamagochi.service;

import com.greenfoxacademy.tamagochi.model.items.Item;
import com.greenfoxacademy.tamagochi.model.pets.*;
import com.greenfoxacademy.tamagochi.repository.PetRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepo pets;
    private final ItemService itemService;

    public List<Pet> getPets() {
        return this.pets.getPets();
    }

    public Optional<Pet> getPet(int petID) {
        return this.pets.getPet(petID);
    }

    public void createPet(PetType type, String name, String description) {
        switch (type) {
            case FOX    : pets.addPet(new Fox(name, description)); break;
            case DOG    : pets.addPet(new Dog(name, description)); break;
            case TIGER  : pets.addPet(new Tiger(name, description)); break;
        }
    }

    public void removePet(int petID) {
        if (getPet(petID).isPresent()) {
            getPets().remove(getPet(petID).get());
        }
    }

    public void useItem(int petID, int itemID) {
        Optional<Pet> pet = getPet(petID);
        Optional<Item> item = itemService.getItem(itemID);
        if(pet.isEmpty() || item.isEmpty()) { return; }

        if(pet.get().use(item.get())) {
            itemService.getItems().remove(item.get());
        }

        if(!pet.get().isAlive()) {
            pets.getPets().remove(pet.get());
        }
    }

    public void sleep(int petID) {
        if (getPet(petID).isPresent()) {
            getPet(petID).get().sleep();
        }
    }
}