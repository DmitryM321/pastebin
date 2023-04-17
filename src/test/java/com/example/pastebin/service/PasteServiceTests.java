package com.example.pastebin.service;

import com.example.pastebin.dto.PasteCreateDTO;
import com.example.pastebin.dto.PasteDTO;
import com.example.pastebin.dto.PasteURLDTO;
import com.example.pastebin.enumss.ExpirationTime;
import com.example.pastebin.enumss.Status;
import com.example.pastebin.exception.PasteNotFoundExeption;
import com.example.pastebin.model.Paste;
import com.example.pastebin.repository.PasteRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
//@ExtendWith(MockitoExtension.class)
public class PasteServiceTests {
    @Mock
    private PasteRepository pasteRepository;
    @InjectMocks
    private PasteService pasteService;
    private PasteDTO pasteDTO;
    private PasteCreateDTO pasteCreateDTO;
    private Paste paste;
    private PasteURLDTO pasteURLDTO;

    @Test
    public void testCreatePaste() throws Exception {
        PasteCreateDTO pasteCreateDTO1 = new PasteCreateDTO();
        pasteCreateDTO1.setTitle("Test1");
        pasteCreateDTO1.setBody("Test1.1");
        pasteCreateDTO1.setStatus(Status.PUBLIC);
        pasteCreateDTO1.setExpirationTime(ExpirationTime.ONE_DAY);
        PasteURLDTO result = pasteService.createPaste(pasteCreateDTO1);
        assertNotNull("Paste URL DTO is null", result);
    }
    @Test
    public void testGetPasteList() {
        List<Paste> pastes = new ArrayList<>();
        pastes.add(new Paste("/my-awesome-pastebin.tld/rZJTCzke", "Title1", "Body1", Instant.now()));
        pastes.add(new Paste("/my-awesome-pastebin.tld/vIaurmua", "Title2", "Body2", Instant.now()));
        when(pasteRepository.findTenLastPaste()).thenReturn(pastes);
        List<PasteDTO> pasteDTOs = pasteService.getPasteList();
        assertEquals(pastes.size(), pasteDTOs.size());
        assertEquals(pastes.get(0).getUrl(), pasteDTOs.get(0).getUrl());
        assertEquals(pastes.get(0).getBody(), pasteDTOs.get(0).getBody());
        assertEquals(pastes.get(0).getLastTime(), pasteDTOs.get(0).getLastTime());
    }
    @Test
    public void testFindUrl() {
    String url = RandomStringUtils.randomAlphabetic(8);
    String title = "Title";
    String body = "Body";
    Instant expiration = Instant.now().plus(Duration.ofMinutes(10));
    Paste paste = new Paste();
        paste.setTitle(title);
        paste.setBody(body);
        paste.setUrl(url);
        paste.setLastTime(expiration);
    PasteDTO pasteDTO1 = new PasteDTO();
        pasteDTO1.setTitle(title);
        pasteDTO1.setBody(body);
        pasteDTO1.setUrl(url);
        pasteDTO1.setLastTime(expiration);
        pasteDTO1.setStatus(Status.PUBLIC);
    when(pasteRepository.findAllByUrlLike(eq(url)))
            .thenReturn(paste);
    PasteDTO pasteDTO2 = pasteService.findByUrl(url);
        pasteDTO2.setStatus(pasteDTO1.getStatus());
    assertEquals(pasteDTO1, pasteDTO2);
}
    @Test
    void testAddPaste() {
        pasteRepository.save(paste);
        verify(pasteRepository, only()).save(paste);
    }
}

