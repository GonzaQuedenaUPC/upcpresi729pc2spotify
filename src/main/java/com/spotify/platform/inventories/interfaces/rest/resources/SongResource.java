package com.spotify.platform.inventories.interfaces.rest.resources;

public record SongResource(Long id, String name, String singer, String group, Long rhythmId, String year) {
}
