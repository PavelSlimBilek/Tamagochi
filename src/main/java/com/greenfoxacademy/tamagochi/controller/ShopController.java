package com.greenfoxacademy.tamagochi.controller;

import com.greenfoxacademy.tamagochi.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/view")
    public String viewShop(Model model,
                           @RequestParam("petID") Optional<Integer> petID) {
        model.addAttribute("shopItemsPool", shopService.getShopItems());
        model.addAttribute("tradePool", shopService.getTradePool());
        model.addAttribute("petID", petID.orElse(null));
        return "viewShop";
    }

    @PostMapping("/toCart")
    public String moveToCart(@RequestParam("petID") Optional<Integer> petID,
                             @RequestParam("itemID") int itemID,
                             RedirectAttributes ra) {
        shopService.moveToCart(itemID);
        ra.addAttribute("petID", petID.orElse(null));
        return "redirect:/shop/view";
    }

    @PostMapping("/fromCart")
    public String moveFromCart(@RequestParam("petID") Optional<Integer> petID,
                               @RequestParam("itemID") int itemID,
                               RedirectAttributes ra) {
        shopService.moveFromCart(itemID);
        ra.addAttribute("petID", petID.orElse(null));
        return "redirect:/shop/view";
    }

    @PostMapping("/buyItems")
    public String buyItems(@RequestParam("petID") Optional<Integer> petID,
                           RedirectAttributes ra) {
        shopService.buyItems();
        ra.addAttribute("petID", petID.orElse(null));
        return "redirect:/shop/view";
    }

    @PostMapping("/clearCart")
    public String clearCart(@RequestParam("petID") Optional<Integer> petID,
                            RedirectAttributes ra) {
        shopService.clearCart();
        ra.addAttribute("petID", petID.orElse(null));
        return "redirect:/shop/view";
    }
}