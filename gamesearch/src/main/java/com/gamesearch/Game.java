package com.gamesearch;

public class Game {
    private final String name;
    private final double price;

    public Game(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }
}
