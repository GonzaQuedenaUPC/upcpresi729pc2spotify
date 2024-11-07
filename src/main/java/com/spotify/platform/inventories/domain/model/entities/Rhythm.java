package com.spotify.platform.inventories.domain.model.entities;

import com.spotify.platform.inventories.domain.model.valueobjects.Rhythms;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "rhythms")
public class Rhythm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, length = 20)
    private Rhythms name;

    public Rhythm() {
    }

    public Rhythm(Rhythms name) {
        this.name = name;
    }

    public String getStringName() {
        return name.toString();
    }

    public static Rhythm getDefaultRhythm() {
        return new Rhythm(Rhythms.SALSA);
    }

    public static Rhythm toRhythmFromName(String name) {
        return new Rhythm(Rhythms.valueOf(name));
    }

    public static List<Rhythm> validateRhythmSet(List<Rhythm> rhythms) {
        if (rhythms == null || rhythms.isEmpty())
            return List.of(getDefaultRhythm());

        return rhythms;
    }
}
