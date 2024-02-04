package com.greenfoxacademy.tamagochi.service;

import com.greenfoxacademy.tamagochi.model.items.Item;
import com.greenfoxacademy.tamagochi.repository.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepo items;

    public List<Item> getItems() {
        return items.getItems();
    }

    public Optional<Item> getItem(int itemId) {
        return items.getItem(itemId);
    }
}