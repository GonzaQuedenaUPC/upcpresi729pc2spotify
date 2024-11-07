package com.spotify.platform.inventories.interfaces.rest.transform;

import com.spotify.platform.inventories.domain.model.aggregates.Song;
import com.spotify.platform.inventories.interfaces.rest.resources.SongResource;

public class SongResourceFromEntityAssembler {

    public static SongResource toResourceFromEntity(Song entity) {
        return new SongResource(
                entity.getId(),
                entity.getName(),
                entity.getSinger(),
                entity.getGroupName(),
                entity.getRhythmId().getId(),
                entity.getYear().toString()
        );
    }
}
