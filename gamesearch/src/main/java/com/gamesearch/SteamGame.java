package com.gamesearch;

public class SteamGame extends Game {
    private final String steamId;

    public SteamGame(String name, double price, ReleaseStatus status, String steamId){
        super(name, price, status, steamId);
        this.steamId = steamId;
    }


}
