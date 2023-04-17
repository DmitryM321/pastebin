package com.example.pastebin.controller;

import com.example.pastebin.dto.PasteCreateDTO;
import com.example.pastebin.dto.PasteDTO;
import com.example.pastebin.dto.PasteURLDTO;
import com.example.pastebin.enumss.ExpirationTime;
import com.example.pastebin.enumss.Status;
import com.example.pastebin.model.Paste;
import com.example.pastebin.repository.PasteRepository;
import com.example.pastebin.service.PasteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PasteControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PasteRepository pasteRepository;
    @Autowired
    ObjectMapper objectMapper;
    Paste paste;
    Status status = Status.PUBLIC;
    ExpirationTime expirationTime = ExpirationTime.TEN_MIN;

    @BeforeEach
    void setUp() {
        paste = new Paste();
        paste.setUrl(UUID.randomUUID().toString());
        paste.setTitle("Title");
        paste.setBody("Body");
        paste.setStatus(status);
        paste.setPublicateTime(Instant.now());
        paste.setLastTime(Instant.now().plus(expirationTime.getDuration()));
        pasteRepository.save(paste);
    }
    @Test
    void testGetLasts() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin.tld/get10"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void testFindByUrl() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin.tld/" + paste.getUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PasteDTO.fromPaste(paste))))
                .andExpect(status().isOk());
    }
}