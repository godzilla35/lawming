package com.lawming.server.controller;

import com.lawming.server.domain.Item;
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
    private final ItemValidator itemValidator;

    // 여기 컨트롤러 안에 요청이 들어오면 init 함수가 항상 실행되고 항상 유효성 검증을 하게됨
    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(itemValidator);
    }

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
        model.addAttribute("item", new Item());

        return "form/addForm";
    }

    // @Validated 이것덕분에  @InitBinder 여기에 매개변수로 item이 날아감
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult);
            return "form/addForm";
        }

        itemService.saveItem(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/form/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, Model model) {
        Item getItem = itemService.findItem(itemId);
        model.addAttribute("item", getItem);
        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemService.updateItem(itemId, item);
        return "redirect:/form/items/{itemId}";
    }
}
