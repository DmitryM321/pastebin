package com.example.pastebin.model;

import com.example.pastebin.enumss.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Data
public class Paste {
    // ID ??????
    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Instant publicDate;
    private Instant expiresDate;

}
