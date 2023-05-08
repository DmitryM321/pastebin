package com.example.pastebin.model;

import com.example.pastebin.enumss.ExpirationTime;
import com.example.pastebin.enumss.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Data
public class Paste {
    @Id
    private String url;
    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Instant publicateTime = Instant.now();
    private Instant lastTime;
    @Enumerated(EnumType.STRING)
    private ExpirationTime expirationTime;

    public Paste(String s, String title1, String body1, Instant now) {
    }

    public Paste() {

    }
}
