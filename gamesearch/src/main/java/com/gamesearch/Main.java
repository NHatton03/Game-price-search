package com.gamesearch;

import java.util.Optional;

public class Main {

    public static void main(String[] args) throws Exception {

    String apiKey = System.getenv("GG_API_KEY");

    GGDealsApi api = new GGDealsApi(apiKey);
    Optional<Game> game = api.getGame("570940");
    game.ifPresentOrElse((g) -> {
        System.out.println("Name: " + g.getName());
        System.out.println("Price: " + g.getPrice());       
    }, () -> {
        System.out.println("Entered game not valid.");
    });
    
    }
}