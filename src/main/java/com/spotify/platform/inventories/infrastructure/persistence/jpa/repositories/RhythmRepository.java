package com.spotify.platform.inventories.infrastructure.persistence.jpa.repositories;

import com.spotify.platform.inventories.domain.model.entities.Rhythm;
import com.spotify.platform.inventories.domain.model.valueobjects.Rhythms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RhythmRepository extends JpaRepository<Rhythm, Long> {

    boolean existsByName(Rhythms name);
}
