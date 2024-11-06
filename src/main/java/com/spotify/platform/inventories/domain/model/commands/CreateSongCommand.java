package com.spotify.platform.inventories.domain.model.commands;

import java.util.Date;

public record CreateSongCommand(String name, String singer, String group, Long rhythmId, Date year) {
}
