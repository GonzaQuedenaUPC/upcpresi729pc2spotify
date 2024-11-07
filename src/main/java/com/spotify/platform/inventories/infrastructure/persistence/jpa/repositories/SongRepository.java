package com.spotify.platform.inventories.infrastructure.persistence.jpa.repositories;

import com.spotify.platform.inventories.domain.model.aggregates.Song;
import com.spotify.platform.inventories.domain.model.entities.Rhythm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    boolean existsByName(String name);
    boolean existsBySinger(String singer);
    boolean existsByGroup(String group);
    boolean existsByRhythmId(Rhythm rhythmId);
}
