package com.gamesearch;

public class Game implements Comparable<Game>{
    private final String name;
    private final double price;
    private final ReleaseStatus status;
    private String storyCompletionTime;
    private final String steamId;
    



    public Game(String name, double price, ReleaseStatus status, String steamId){
        this.name = name;
        this.price = price;
        this.status = status;
        this.steamId = steamId;
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

    public void setTime(String storyCompletionTime){
        this.storyCompletionTime = storyCompletionTime;
    }

    public String getTime(){
        return storyCompletionTime;
    }

    public String getId(){
        return steamId;
    }

    public String toString(){
        return String.format("[%s] %s : EUR %.2f ",status, name, price);
    }

    @Override
    public int compareTo(Game o) {
        return (int)(o.price - this.price);
    }
}
