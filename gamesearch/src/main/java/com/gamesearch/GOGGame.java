package com.gamesearch;

public class GOGGame extends Game {
    private final String GOGId;

    public GOGGame(String name, double price, ReleaseStatus status, String GOGId){
        super(name, price, status, GOGId);
        this.GOGId = GOGId;
    }


}
