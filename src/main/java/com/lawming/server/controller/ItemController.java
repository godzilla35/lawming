package com.lawming.server.controller;

import com.lawming.server.domain.Item;
import com.lawming.server.domain.ItemSaveForm;
import com.lawming.server.domain.ItemUpdateForm;
import com.lawming.server.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    //private final ItemValidator itemValidator;


    @GetMapping
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        for(Item item : items) {
            log.info("id : {} city : {} price : {}", item.getId(), item.getCity(), item.getPrice());
        }
        model.addAttribute("items", items);

        return "form/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        model.addAttribute("item", item);
        return "form/item";
    }

    @GetMapping("/add")
    public String addItem(Model model) {
        model.addAttribute("itemSaveForm", new ItemSaveForm());

        return "form/addForm";
    }

    // @Validated 이것덕분에  @InitBinder 여기에 매개변수로 item이 날아감
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute ItemSaveForm itemSaveForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult);
            return "form/addForm";
        }
        Item item = new Item();
        item.setPrice(itemSaveForm.getPrice());
        item.setCity(itemSaveForm.getCity());
        item.setDueDate(itemSaveForm.getDueDate());

        itemService.saveItem(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/form/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, Model model) {
        log.info("===### id = {}", itemId);
        Item getItem = itemService.findItem(itemId);

        model.addAttribute("itemUpdateForm", getItem);
        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, @Validated @ModelAttribute ItemUpdateForm itemUpdateForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult);
            return "form/editForm";
        }

        Item item = new Item();
        item.setPrice(itemUpdateForm.getPrice());
        item.setCity(itemUpdateForm.getCity());
        item.setDueDate(itemUpdateForm.getDueDate());

        itemService.updateItem(itemId, item);
        return "redirect:/form/items/{itemId}";
    }
}
