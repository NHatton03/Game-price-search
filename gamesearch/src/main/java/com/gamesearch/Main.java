package com.gamesearch;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

    // GOGApi gog = new GOGApi();
    // System.out.println(gog.buildGame("1193046833"));  
    

    HltbApi HowLongToBeat = new HltbApi();
    String apiKey = System.getenv("GG_API_KEY");
    SteamApi steam = new SteamApi();
    buildMenu();
    String keyWord = scanner.nextLine();
    var map = steam.getId(keyWord);

    GGDealsApi api = new GGDealsApi(apiKey);

    String[] stringArray = Arrays.copyOf(map.keySet().toArray(), map.keySet().toArray().length, String[].class);
    
    var games = api.getGames(stringArray);
    for(Game g : games){
        System.out.println(g);
    }
    
    System.out.println("Select game");
    int selection = scanner.nextInt();
    scanner.nextLine();
    System.out.println(games.get(selection));
    Game game = games.get(selection);
    if(game.getTime() == null){
        game.setTime(HowLongToBeat.getTime(game.getId()));
    }
    System.out.printf("[Time to beat main story] %s", game.getTime());

    }


    public static void buildMenu(){
        System.out.println("*********************************");
        System.out.println("*\tGame Price Search\t*");
        System.out.println("*\t\t\t\t*");
        System.out.println("*********************************");
        System.out.print("Enter a game name: ");
    }
}