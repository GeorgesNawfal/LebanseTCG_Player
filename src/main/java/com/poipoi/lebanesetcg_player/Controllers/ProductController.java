package com.poipoi.lebanesetcg_player.Controllers;

import com.poipoi.lebanesetcg_player.Models.DTO.ProductPatchRequest;
import com.poipoi.lebanesetcg_player.Models.Entities.Game;
import com.poipoi.lebanesetcg_player.Models.Entities.Product;
import com.poipoi.lebanesetcg_player.Repositories.GameRepository;
import com.poipoi.lebanesetcg_player.Repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final GameRepository gameRepository;

    public ProductController(ProductRepository productRepository, GameRepository gameRepository) {
        this.productRepository = productRepository;
        this.gameRepository = gameRepository;

    }

    /**
     * GET Endpoint to retrieve all Games
     * URL: GET http://localhost:8080/api/games
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> product = productRepository.findAll();
        return ResponseEntity.ok(product);
    }

    /**
     * GET Endpoint to retrieve a single Game by ID
     * URL: GET http://localhost:8080/api/games/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST Endpoint to create a new Game
     * URL: POST http://localhost:8080/api/games
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long id,
                                                @RequestBody ProductPatchRequest patch) {
        return productRepository.findById(id)
                .map(product -> {
                    if (patch.productType() != null) product.setProductType(patch.productType());
                    if (patch.name() != null)        product.setProductName(patch.name());
                    if (patch.marketPrice() != null) product.setMarketPrice(patch.marketPrice());
                    if (patch.imgURL() != null)      product.setImgURL(patch.imgURL());
                    if (patch.gameId() != null) {
                        Game game = gameRepository.findById(patch.gameId())
                                .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST, "Game not found: " + patch.gameId()));
                        product.setGame(game);
                    }
                    return ResponseEntity.ok(productRepository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

}