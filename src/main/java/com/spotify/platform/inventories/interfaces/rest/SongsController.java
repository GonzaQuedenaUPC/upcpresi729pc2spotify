package com.spotify.platform.inventories.interfaces.rest;

import com.spotify.platform.inventories.domain.model.aggregates.Song;
import com.spotify.platform.inventories.domain.services.SongCommandService;
import com.spotify.platform.inventories.interfaces.rest.resources.CreateSongResource;
import com.spotify.platform.inventories.interfaces.rest.resources.SongResource;
import com.spotify.platform.inventories.interfaces.rest.transform.CreateSongCommandFromResourceAssembler;
import com.spotify.platform.inventories.interfaces.rest.transform.SongResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="/api/v1/songs", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@Tag(name = "Songs", description = "Songs API")
public class SongsController {

    private final SongCommandService songCommandService;

    public SongsController(SongCommandService songCommandService) {
        this.songCommandService = songCommandService;
    }
    @Operation(
            summary = "Create a song",
            description = "Create a song with the given information"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Song created"),
                    @ApiResponse(responseCode = "400", description = "Invalid data"),
            }
    )
    @PostMapping
    public ResponseEntity<SongResource> createSong(@RequestBody CreateSongResource resource) {

        Optional<Song> song = this.songCommandService
                .handle(CreateSongCommandFromResourceAssembler.toCommandFromResource(resource));

        return song.map(source ->
                new ResponseEntity<>(SongResourceFromEntityAssembler.toResourceFromEntity(source),
                        HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
