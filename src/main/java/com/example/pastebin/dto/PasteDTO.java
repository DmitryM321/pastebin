package com.example.pastebin.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class PasteDTO {
    private String title;
    private String body;
    private Instant publicDate;
}
