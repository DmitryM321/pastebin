package com.example.pastebin.dto;

import com.example.pastebin.enumss.ExpirationTime;
import com.example.pastebin.enumss.Status;
import com.example.pastebin.model.Paste;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class PasteCreateDTO {
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private String url;
        private String title;
        private String body;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private Instant publicateTime;// = Instant.now();
        @Enumerated(EnumType.STRING)
        private Status status;
        private ExpirationTime expirationTime;
        @Enumerated(EnumType.STRING)
        private Instant lastTime;
        public Paste  to() {
            Paste paste = new Paste();
            paste.setUrl(this.getUrl());
            paste.setTitle(this.getTitle());
            paste.setBody(this.getBody());
            paste.setStatus(this.getStatus());
            paste.setLastTime(this.getLastTime());
            paste.setExpirationTime(this.expirationTime);
            return paste;
        }
}