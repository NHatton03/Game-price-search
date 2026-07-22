package com.gamesearch;

public class Game {
    private final String name;
    private final String price;

    public Game(String name, String price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getPrice(){
        return price;
    }
}
