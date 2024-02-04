package com.greenfoxacademy.tamagochi.service;

import com.greenfoxacademy.tamagochi.model.items.Item;
import com.greenfoxacademy.tamagochi.repository.ShopRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepo shopRepo;
    private final ItemService itemService;

    public void moveToCart(int itemID) {
        Optional<Item> optItem = shopRepo.getItem(itemID, shopRepo.getShopItems());
        if (optItem.isPresent()) {
            Item item = optItem.get();
            shopRepo.getTradePool().add(item);
            shopRepo.getShopItems().remove(item);
        }
    }

    public void moveFromCart(int itemID) {
        Optional<Item> optItem = shopRepo.getItem(itemID, shopRepo.getTradePool());
        if (optItem.isPresent()) {
            Item item = optItem.get();
            shopRepo.getShopItems().add(item);
            shopRepo.getTradePool().remove(item);
        }
    }

    public void buyItems() {
        for (Item i : shopRepo.getTradePool()) {
            itemService.getItems().add(i);
        }
        shopRepo.getTradePool().clear();
    }

    public void clearCart() {
        for (Item i : shopRepo.getTradePool()) {
           shopRepo.getShopItems().add(i);
        }
        shopRepo.getTradePool().clear();
    }

    public List<Item> getShopItems() {
        return shopRepo.getShopItems();
    }

    public List<Item> getTradePool() {
        return shopRepo.getTradePool();
    }
}