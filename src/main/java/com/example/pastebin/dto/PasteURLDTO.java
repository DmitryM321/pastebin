package com.example.pastebin.dto;

import com.example.pastebin.model.Paste;
import lombok.Data;

@Data
public class PasteURLDTO {
    private String url;

    public static PasteURLDTO fromPaste(Paste paste) {
        PasteURLDTO dto = new PasteURLDTO();
        dto.setUrl(paste.getUrl());
        return dto;
    }
    public PasteDTO toPaste() {
            PasteDTO paste = new PasteDTO();
            paste.setUrl(this.getUrl());
            return paste;
        }
    public PasteURLDTO(String url) {
        this.url = url;
    }
    public PasteURLDTO() {
    }
}
