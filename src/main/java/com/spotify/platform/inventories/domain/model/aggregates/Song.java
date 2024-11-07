package com.spotify.platform.inventories.domain.model.aggregates;

import com.spotify.platform.inventories.domain.model.commands.CreateSongCommand;
import com.spotify.platform.inventories.domain.model.entities.Rhythm;
import com.spotify.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Entity
public class Song extends AuditableAbstractAggregateRoot<Song> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true, nullable = false)
    String name;

    @NotBlank
    @Size(max = 80)
    String singer;

    @NotBlank
    @Size(max = 100)
    String groupName;

    @NotNull
    @ManyToOne
    @JoinColumn(name="rhythm_id", nullable = false)
    Rhythm rhythmId;

    @Temporal(TemporalType.DATE)
    Date year;

    public Song() {
    }

    public Song(CreateSongCommand command, Rhythm rhythm) {
        this.name = command.name();
        this.singer = command.singer();
        this.groupName = command.group();
        this.rhythmId = rhythm;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            this.year = dateFormat.parse(command.year());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid year format: the year must be in 'yyyy' format", e);
        }
    }

    public Long geRhythmId() {
        return rhythmId.getId();
    }
}
