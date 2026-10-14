package com.poipoi.lebanesetcg_player.Controllers;

import com.poipoi.lebanesetcg_player.Models.Entities.Game;
import com.poipoi.lebanesetcg_player.Repositories.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * GET Endpoint to retrieve all Games
     * URL: GET http://localhost:8080/api/games
     */
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return ResponseEntity.ok(games);
    }

    /**
     * GET Endpoint to retrieve a single Game by ID
     * URL: GET http://localhost:8080/api/games/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST Endpoint to create a new Game
     * URL: POST http://localhost:8080/api/games
     */
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game savedGame = gameRepository.save(game);
        return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
    }

    /**
     * PUT Endpoint to update an existing Game
     * URL: PUT http://localhost:8080/api/games/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game updatedGame) {
        return gameRepository.findById(id)
                .map(existingGame -> {
                    // Update the fields of the existing game with the new data
                    // Replace these field setters with whatever properties your Game entity has
                    existingGame.setName(updatedGame.getName());
                    // existingGame.setDescription(updatedGame.getDescription());

                    Game savedGame = gameRepository.save(existingGame);
                    return ResponseEntity.ok(savedGame);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}