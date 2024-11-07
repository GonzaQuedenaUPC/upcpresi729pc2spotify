package com.spotify.platform.inventories.interfaces.rest.resources;

public record CreateSongResource(String name, Long rhythmId, String singer, String group, String year) {
}
