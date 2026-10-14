package com.poipoi.lebanesetcg_player.Repositories;

import com.poipoi.lebanesetcg_player.Models.Entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {}
