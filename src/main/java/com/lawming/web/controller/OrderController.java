package com.lawming.web.controller;

import com.lawming.domain.item.Item;
import com.lawming.domain.member.Member;
import com.lawming.domain.order.Order;
import com.lawming.web.argumentresolver.Login;
import com.lawming.web.service.ItemService;
import com.lawming.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final ItemService itemService;
    private final OrderService orderService;

    // public String item(@PathVariable Long itemId, @Login Member loginMember, Model model) {

    //@GetMapping("/{itemId}")
    public String order(@PathVariable Long itemId, Model model) {
        List<Order> orders = orderService.findAllByItemId(itemId);

        model.addAttribute("orders", orders);

        return "form/orders";
    }


    @PostMapping("/{itemId}")
    public String makeOrder(@PathVariable Long itemId,
                           @Login Member loginMember,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        orderService.order(loginMember.getId(), itemId);

        redirectAttributes.addAttribute("itemId", itemId);
        return "redirect:/form/items/{itemId}";
    }
}
