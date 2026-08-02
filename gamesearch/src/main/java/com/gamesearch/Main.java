package com.gamesearch;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

    GOGApi gog = new GOGApi();
    gog.getGameTitle("1207658691");    

    String apiKey = System.getenv("GG_API_KEY");
    SteamApi steam = new SteamApi();
    buildMenu();
    String keyWord = scanner.nextLine();
    var map = steam.getId(keyWord);

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


    public static void buildMenu(){
        System.out.println("*********************************");
        System.out.println("*\tGame Price Search\t*");
        System.out.println("*\t\t\t\t*");
        System.out.println("*********************************");
        System.out.print("Enter a game name: ");
    }
}