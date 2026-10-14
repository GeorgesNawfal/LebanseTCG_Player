package com.poipoi.lebanesetcg_player.Models.DTO;

public record ProductPatchRequest(
        String productType,
        String name,
        Long gameId,
        Double marketPrice,   // match the type used in your Product entity
        String imgURL
) {}