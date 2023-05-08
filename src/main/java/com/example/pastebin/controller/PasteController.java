package com.example.pastebin.controller;

import com.example.pastebin.dto.PasteCreateDTO;
import com.example.pastebin.dto.PasteURLDTO;
import com.example.pastebin.dto.PasteDTO;
import com.example.pastebin.service.PasteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-awesome-pastebin.tld")
public class PasteController {
    private final PasteService pasteService;
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @PostMapping
    public PasteURLDTO createPaste(@RequestBody PasteCreateDTO pasteCreateDTO) {
        return pasteService.createPaste(pasteCreateDTO);
    }
//    @GetMapping
    @GetMapping("/get10")
    public List<PasteDTO> getPasteList() {
        return pasteService.getPasteList();
    }
    @GetMapping(value = "/{url}")
    public PasteDTO findByUrl(@PathVariable String url) {
        return pasteService.findByUrl(url);
    }
    @GetMapping("/search")
    public ResponseEntity<List<PasteDTO>> searchBy(@RequestParam(required = false) String title,
                                                   @RequestParam(required = false) String body){
        return ResponseEntity.ok(pasteService.search(title, body));
    }
}
