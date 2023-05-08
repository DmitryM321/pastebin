package com.example.pastebin.dto;

import com.example.pastebin.enumss.Status;
import com.example.pastebin.model.Paste;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Data
public class PasteDTO {
    private String url;
    private String title;
    private String body;
    private Instant lastTime;
 //   @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private Status status;
    public static PasteDTO fromPaste(Paste paste) {
        PasteDTO dto = new PasteDTO();
        dto.setUrl(paste.getUrl());
        dto.setBody(paste.getBody());
        dto.setTitle(paste.getTitle());
        dto.setLastTime(paste.getLastTime());
        dto.setStatus(paste.getStatus());
        return dto;
    }
}
