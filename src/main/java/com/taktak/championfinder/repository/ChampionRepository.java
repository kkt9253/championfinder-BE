package com.taktak.championfinder.repository;

import com.taktak.championfinder.model.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, Integer> {
}
