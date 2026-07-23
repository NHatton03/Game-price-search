package com.gamesearch;

import java.util.Optional;

public class Main {

    public static void main(String[] args) throws Exception {

    String apiKey = System.getenv("GG_API_KEY");
    SteamApi steam = new SteamApi("Portal");
    System.out.println(steam.getId());

    GGDealsApi api = new GGDealsApi(apiKey);
    Optional<Game> game = api.getGame("1113550");
    game.ifPresentOrElse((g) -> {
        System.out.println("Name: " + g.getName());
        System.out.println("Price: " + g.getPrice());       
    }, () -> {
        System.out.println("Entered game not valid.");
    });
    
    }
}