package com.example.pastebin.sheduler;

import com.example.pastebin.repository.PasteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Component
public class ClearPaste {
    private final PasteRepository pastaRepository;

    public ClearPaste(PasteRepository pastaRepository) {
        this.pastaRepository = pastaRepository;
    }
    @Scheduled(fixedRateString = "${pastebin.timer.rate-minutes}", timeUnit = TimeUnit.MINUTES)
    @Transactional
    public void clearTokens() {
        pastaRepository.deleteAllByLastTimeIsBefore(Instant.now());
    }

}


