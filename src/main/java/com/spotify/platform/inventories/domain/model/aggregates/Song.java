package com.spotify.platform.inventories.domain.model.aggregates;

import com.spotify.platform.inventories.domain.model.commands.CreateSongCommand;
import com.spotify.platform.inventories.domain.model.entities.Rhythm;
import com.spotify.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Song extends AuditableAbstractAggregateRoot<Song> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank
    @Size(max = 80)
    private String singer;

    @NotBlank
    @Size(max = 100)
    private String group;

    @NotNull
    @ManyToOne
    @JoinColumn(name="rhythm_id", nullable = false)
    Rhythm rhythmId;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date year;

    public Song() {
    }

    public Song(CreateSongCommand command, Rhythm rhythm) {
        this.name = command.name();
        this.singer = command.singer();
        this.group = command.group();
        this.rhythmId = rhythm;
        this.year = command.year();
    }

    public Long getGroupId() {
        return rhythmId.getId();
    }
}
