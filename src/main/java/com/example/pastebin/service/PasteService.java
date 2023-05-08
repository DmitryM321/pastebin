package com.example.pastebin.service;

import com.example.pastebin.dto.PasteCreateDTO;
import com.example.pastebin.dto.PasteURLDTO;
import com.example.pastebin.exception.BadRequestException;
import com.example.pastebin.exception.PasteNotFoundExeption;
import com.example.pastebin.model.Paste;
import com.example.pastebin.repository.PasteRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.pastebin.dto.PasteDTO;
import java.time.Instant;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.pastebin.repository.specification.Specific.*;

@Service
public class PasteService {
    private final PasteRepository pasteRepository;

    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    public PasteURLDTO createPaste(PasteCreateDTO pasteCreateDTO) {
        Paste paste = pasteCreateDTO.to();
        paste.setUrl(RandomStringUtils.randomAlphabetic(8));
        paste.setLastTime(Instant.now()
                .plus(pasteCreateDTO.getExpirationTime().getDuration()));
        paste.setStatus(pasteCreateDTO.getStatus());
        pasteRepository.save(paste);
        return PasteURLDTO.fromPaste(paste);
    }
    public List<PasteDTO> getPasteList() {
        return pasteRepository.findTenLastPaste()
                .stream()
                .map(PasteDTO::fromPaste)
                .collect(Collectors.toList());
    }
    public PasteDTO findByUrl(String url) {
        Optional<Paste> optionalPaste = Optional.ofNullable(pasteRepository.findAllByUrlLike(url));
        Paste paste = optionalPaste
                .filter(p -> p.getLastTime().isAfter(Instant.now()))
                .orElseThrow(PasteNotFoundExeption::new);
        return PasteDTO.fromPaste(paste);
    }
    public List<PasteDTO> search(String title, String body) {
        List<Paste> pastes = pasteRepository.findAll(Specification.where(
                        byTitle(title))
                .and(byBody(body))
                .and(byNotExpired()));
        if (pastes.isEmpty()) {
            throw new BadRequestException();
        }
        return pastes.stream()
                .map(PasteDTO::fromPaste)
                .collect(Collectors.toList());
    }
}

