package com.spotify.platform.inventories.domain.services;

import com.spotify.platform.inventories.domain.model.aggregates.Song;
import com.spotify.platform.inventories.domain.model.commands.CreateSongCommand;

import java.util.Optional;

public interface SongCommandService {
    Optional<Song> handle(CreateSongCommand command);
}
