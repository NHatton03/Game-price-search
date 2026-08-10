package com.gamesearch;

public abstract class Game implements Comparable<Game>{
    private final String name;
    private final double price;
    private final ReleaseStatus status;
    protected String storyCompletionTime;
    private final String Id;
    



    public Game(String name, double price, ReleaseStatus status, String Id){
        this.name = name;
        this.price = price;
        this.status = status;
        this.Id = Id;
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
        return Id;
    }

    public String toString(){
        return String.format("[%s] %s : EUR %.2f ",status, name, price);
    }

    @Override
    public int compareTo(Game o) {
        return (int)(o.price - this.price);
    }
}
