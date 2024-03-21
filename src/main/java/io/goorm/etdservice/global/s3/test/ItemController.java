package io.goorm.etdservice.global.s3.test;

import io.goorm.etdservice.global.s3.upload.AWSS3FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final AWSS3FileService awss3FileService;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form){
        return "item-form";
    }

    @ResponseBody
    @PostMapping("/items/new")
    public Item setItem(@ModelAttribute ItemForm form , RedirectAttributes redirectAttributes) throws IOException {

        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setAttachFile(form.getAttachFile().getOriginalFilename());
        awss3FileService.putObject(form.getAttachFile(), "game-image",  form.getAttachFile().getOriginalFilename());

        return item;
    }


}