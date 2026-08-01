package com.gamesearch;

public class Game implements Comparable<Game>{
    private final String name;
    private final double price;
    private final ReleaseStatus status;
    

    // public Game(String name, ReleaseStatus status){
    //     this.price = 0;
    //     this.name = name;
    //     this.status = status;
    // }

    public Game(String name, double price, ReleaseStatus status){
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public ReleaseStatus getStatus(){
        return status;
    }

    public String toString(){
        return String.format("[%s] %s : %.2f ",status, name, price);
    }

    @Override
    public int compareTo(Game o) {
        return (int)(this.price - o.price);
    }
}
