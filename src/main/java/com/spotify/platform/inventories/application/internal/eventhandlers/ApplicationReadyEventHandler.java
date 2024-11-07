package com.spotify.platform.inventories.application.internal.eventhandlers;

import com.spotify.platform.inventories.domain.model.commands.SeedRhythmsCommand;
import com.spotify.platform.inventories.domain.services.RhythmCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Service
public class ApplicationReadyEventHandler {
    private final RhythmCommandService rhythmCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(RhythmCommandService rhythmCommandService) {
        this.rhythmCommandService = rhythmCommandService;
    }

    /**
     * Event listener for the application ready event.
     * It seeds the groups in the database.
     * @param event the application ready event
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Application {} is ready at {}", applicationName, currentTimestamp());
        var seedRhythmCommand = new SeedRhythmsCommand();
        rhythmCommandService.handle(seedRhythmCommand);
        LOGGER.info("Seed groups command handled at {}", currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
