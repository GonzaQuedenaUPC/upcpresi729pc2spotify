package com.spotify.platform.inventories.application.internal.commandservices;

import com.spotify.platform.inventories.domain.model.aggregates.Song;
import com.spotify.platform.inventories.domain.model.commands.CreateSongCommand;
import com.spotify.platform.inventories.domain.model.entities.Rhythm;
import com.spotify.platform.inventories.domain.services.SongCommandService;
import com.spotify.platform.inventories.infrastructure.persistence.jpa.repositories.RhythmRepository;
import com.spotify.platform.inventories.infrastructure.persistence.jpa.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongCommandServiceImpl implements SongCommandService {
    private final SongRepository songRepository;
    private final RhythmRepository rhythmRepository;

    public SongCommandServiceImpl(SongRepository songRepository, RhythmRepository rhythmRepository) {
        this.songRepository = songRepository;
        this.rhythmRepository = rhythmRepository;
    }

    @Override
    public Optional<Song> handle(CreateSongCommand command) {

        Rhythm rhythm = rhythmRepository.findById(command.rhythmId())
                .orElseThrow(() -> new IllegalArgumentException("Rhythm not found"));

        if (songRepository.existsByNameAndSingerAndGroupNameAndRhythmId(command.name(),
                command.singer(), command.group(), rhythm)) {

            throw new IllegalArgumentException("Song already exists");
        }

        Song song = new Song(command, rhythm);

        var songSaved = songRepository.save(song);

        return Optional.of(songSaved);
    }
}
