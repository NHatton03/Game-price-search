package com.gamesearch;

public class Game {
    private final String name;
    private final double price;
    private String message;

    public Game(String name, String message){
        this.price = 0;
        this.name = name;
        this.message = message;
    }

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

    public String getMessage(){
        return message;
    }
}
