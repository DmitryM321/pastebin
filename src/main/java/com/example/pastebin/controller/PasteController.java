package com.example.pastebin.controller;

import com.example.pastebin.service.PasteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pastebin")
public class PasteController {
    private final PasteService pasteService;


    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }
}
