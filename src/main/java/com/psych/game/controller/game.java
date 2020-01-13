package com.psych.game.controller;

import com.psych.game.Utils;
import com.psych.game.model.Game;
import com.psych.game.model.GameMode;
import com.psych.game.model.Player;
import com.psych.game.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class game {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerAnswerRepository playerAnswerRepository;

    @Autowired
    RoundRepository roundRepository;

    @GetMapping("/create/{pid}/{gm}/{nr}")
    public String createGame(@PathVariable(value = "pid") Long playerId,
                             @PathVariable(value = "gm") int gameMode,
                             @PathVariable(value = "nr") int numRounds) throws Exception {
        Player player = playerRepository.findById(playerId).orElseThrow(()-> new Exception("Something went wrong!"));
        GameMode mode = GameMode.IS_THIS_A_fACT;

        Game game=new Game();

        game.setGameMode(mode);
        game.setNumRounds(numRounds);
        game.setLeader(player);
        game.getPlayers().add(player);

        gameRepository.save(game);

        return "" + game.getId() + "-" + Utils.getSecretCodeFromId(game.getId());
    }

    @GetMapping("/create/{pid}/{gc}")
    public String joinGame(@PathVariable(value = "pid") Long playerId,
                             @PathVariable(value = "gm") String gameCode) throws Exception {
        Game game = gameRepository.findById(Utils.getGameIdFromSecretCode(gameCode)).orElseThrow(()-> new Exception("Something went wrong!"));;

        Player player = playerRepository.findById(playerId).orElseThrow(()-> new Exception("Something went wrong!"));

        game.getPlayers().add(player);

        gameRepository.save(game);

        return "joined sucessfully";
    }
}
