package com.spotify.platform.inventories.application.internal.commandservices;

import com.spotify.platform.inventories.domain.model.commands.SeedRhythmsCommand;
import com.spotify.platform.inventories.domain.model.entities.Rhythm;
import com.spotify.platform.inventories.domain.model.valueobjects.Rhythms;
import com.spotify.platform.inventories.domain.services.RhythmCommandService;
import com.spotify.platform.inventories.infrastructure.persistence.jpa.repositories.RhythmRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RhythmCommandServiceImpl implements RhythmCommandService {
    private final RhythmRepository rhythmRepository;

    public RhythmCommandServiceImpl(RhythmRepository rhythmRepository) {
        this.rhythmRepository = rhythmRepository;
    }

    @Override
    public void handle(SeedRhythmsCommand command) {
        Arrays.stream(Rhythms.values()).forEach(rhythm -> {
            if (!rhythmRepository.existsByName(rhythm)) {
                rhythmRepository.save(new Rhythm(Rhythms.valueOf(rhythm.name())));
            }
        });
    }
}
