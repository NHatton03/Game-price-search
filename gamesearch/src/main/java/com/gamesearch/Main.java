package com.gamesearch;

import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws Exception {

    String apiKey = System.getenv("GG_API_KEY");
    SteamApi steam = new SteamApi();
    var map = steam.getId("The Expanse");

    GGDealsApi api = new GGDealsApi(apiKey);
    
    // game.ifPresentOrElse((g) -> {
    //     System.out.println("Name: " + g.getName());
    //     System.out.println("Price: " + g.getPrice());       
    // }, () -> {
    //     System.out.println("Entered game not valid.");
    // });

    for (Map.Entry<String,String> pair: map.entrySet()){
        Optional<Game> game = api.getGame(pair.getKey());
        game.ifPresent((g) -> {
            if(g.getPrice() == 0.0){
                System.out.println(g.getName() + " " + g.getMessage());
            }else {
            System.out.println(g.getName() + " " + g.getPrice());
            }
        });
    }
    
    }
}