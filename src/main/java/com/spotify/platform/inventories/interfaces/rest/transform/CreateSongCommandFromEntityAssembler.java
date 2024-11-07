package com.spotify.platform.inventories.interfaces.rest.transform;

import com.spotify.platform.inventories.domain.model.commands.CreateSongCommand;

public class CreateSongCommandFromEntityAssembler {

    public static CreateSongCommand toCommandFromResource(CreateSongCommand resource) {
        return new CreateSongCommand(
                resource.name(),
                resource.singer(),
                resource.group(),
                resource.rhythmId(),
                resource.year()
        );
    }
}
