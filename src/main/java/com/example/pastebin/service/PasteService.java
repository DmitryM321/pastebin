package com.example.pastebin.service;

import com.example.pastebin.repository.PasteRepository;
import org.springframework.stereotype.Service;

@Service
public class PasteService {
    private final PasteRepository  pasteRepository;


    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }
}
