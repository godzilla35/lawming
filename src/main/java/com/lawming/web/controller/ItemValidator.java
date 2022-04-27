package com.lawming.web.controller;

import com.lawming.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    // 넘어오는 클래스가 지원하는 클래스인지 구분
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

//        if(item.getPrice() == null) {
//            bindingResult.addError(new FieldError("item", "price","가격이 올바르지 않습니다."));
//        }
        if(item.getPrice() != null) {
            if(item.getPrice() <= 0 || item.getPrice() > 1000000) {
                //bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{0, 1000000},null));
                errors.rejectValue("price", "range", new Object[]{0, 1000000}, null);
            }

        }
    }
}
