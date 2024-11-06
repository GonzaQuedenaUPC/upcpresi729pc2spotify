package com.spotify.platform.inventories.domain.services;

import com.spotify.platform.inventories.domain.model.commands.SeedSongsCommand;

public interface RhythmCommandService {
    void handle (SeedSongsCommand command);
}
