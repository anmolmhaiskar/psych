package com.psych.game.repository;

import com.psych.game.model.Game;
import com.psych.game.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}

