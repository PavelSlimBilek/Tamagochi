package com.greenfoxacademy.tamagochi.controller;

import com.greenfoxacademy.tamagochi.model.pets.Pet;
import com.greenfoxacademy.tamagochi.model.pets.PetType;
import com.greenfoxacademy.tamagochi.service.ItemService;
import com.greenfoxacademy.tamagochi.service.PetService;
import com.greenfoxacademy.tamagochi.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final ItemService itemService;

    @GetMapping("/view")
    public String viewPet(Model model, @RequestParam("petID") int petID) {
        Optional<Pet> optPet = petService.getPet(petID);
        if (optPet.isEmpty()) { return "redirect:/pet/pets"; }
        model.addAttribute("itemPool", itemService.getItems());
        model.addAttribute("pet", optPet.get());
        model.addAttribute("sleepThreshold", Pet.SLEEP_THRESHOLD);
        return "viewPet";
    }

    @PostMapping("/use")
    public String useItem(@RequestParam("petID") int petID,
                          @RequestParam("itemID") int itemID,
                          RedirectAttributes ra) {
        petService.useItem(petID, itemID);
        ra.addAttribute("petID", petID);
        return "redirect:/pet/view";
    }

    @GetMapping("/createForm")
    public String createForm(Model model) {
        model.addAttribute("typePool", PetType.getTypes());
        model.addAttribute("randomName", Util.getRandomName());
        model.addAttribute("randomDescription", Util.getRandomDescription());
        return "createPet";
    }

    @GetMapping("/createBlank")
    public String createBlankForm(Model model) {
        model.addAttribute("typePool", PetType.getTypes());
        return "createPet";
    }

    @PostMapping("/create")
    public String createPet(Model model,
                            @RequestParam("type") PetType type,
                            @RequestParam("name") String name,
                            @RequestParam("description") String description) {
        petService.createPet(type, name, description);
        model.addAttribute("petsPool", petService.getPets());
        model.addAttribute("typePool", PetType.getTypes());
        return "redirect:/pet/pets";
    }

    @GetMapping("/removeForm")
    public String removeForm(Model model) {
        model.addAttribute("petsPool", petService.getPets());
        return "remove";
    }

    @PostMapping("/removePet")
    public String removePet(@RequestParam("petID") int petID) {
        petService.removePet(petID);
        return "redirect:/pet/removeForm";
    }

    @PostMapping("/removeAll")
    public String removeAll() {
        petService.getPets().clear();
        return "redirect:/pet/createForm";
    }

    @GetMapping("/pets")
    public String showPets(Model model) {
        model.addAttribute("petsPool", petService.getPets());
        model.addAttribute("typePool", PetType.getTypes());
        return "pets";
    }

    @PostMapping("/sleep")
    public String sleep(@RequestParam("petID") int petID,
                        RedirectAttributes ra) {
        petService.sleep(petID);
        ra.addAttribute("petID", petID);
        return "redirect:/pet/view";
    }
}