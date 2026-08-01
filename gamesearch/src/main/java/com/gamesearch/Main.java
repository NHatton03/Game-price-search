package com.gamesearch;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

    String apiKey = System.getenv("GG_API_KEY");
    SteamApi steam = new SteamApi();
    var map = steam.getId("Yakuza");

    GGDealsApi api = new GGDealsApi(apiKey);

    String[] stringArray = Arrays.copyOf(map.keySet().toArray(), map.keySet().toArray().length, String[].class);
    
    var t = api.getGames(stringArray);
    for(Game g : t){
        System.out.println(g);
    }
    
    // for (Map.Entry<String,String> pair: map.entrySet()){
    //     Optional<Game> game = api.getGame(pair.getKey());
    //     game.ifPresent((g) -> {
    //         System.out.println(g);
    //     });
    // }
    
    }
}