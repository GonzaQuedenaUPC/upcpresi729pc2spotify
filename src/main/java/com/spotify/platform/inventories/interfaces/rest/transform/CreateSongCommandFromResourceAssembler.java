package com.spotify.platform.inventories.interfaces.rest.transform;

import com.spotify.platform.inventories.domain.model.commands.CreateSongCommand;
import com.spotify.platform.inventories.interfaces.rest.resources.CreateSongResource;

public class CreateSongCommandFromResourceAssembler {

    public static CreateSongCommand toCommandFromResource(CreateSongResource resource) {
        return new CreateSongCommand(
                resource.name(),
                resource.singer(),
                resource.group(),
                resource.rhythmId(),
                resource.year()
        );
    }
}
