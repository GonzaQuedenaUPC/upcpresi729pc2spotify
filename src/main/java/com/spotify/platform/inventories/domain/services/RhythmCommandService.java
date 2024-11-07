package com.spotify.platform.inventories.domain.services;

import com.spotify.platform.inventories.domain.model.commands.SeedRhythmsCommand;

public interface RhythmCommandService {
    void handle (SeedRhythmsCommand command);
}
