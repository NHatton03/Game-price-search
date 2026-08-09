package com.gamesearch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GOGApi {
    
    private final HttpClient client;

    static ObjectMapper mapper = new ObjectMapper();
    public GOGApi(){
        client = HttpClient.newBuilder().build();
    }

    public Game buildGame(String id){
        String title = getGameTitle(id);
        double price = getGamePrice(id);
        boolean isGameReleased = isGameReleased(id);
        if (isGameReleased){
            return new Game(title, price, ReleaseStatus.Released ,"Undefined");
        }
        return new Game(title, price, ReleaseStatus.Unreleased ,"Undefined");
    }

    public boolean isGameReleased(String id){
        String url = String.format(
            "https://api.gog.com/v2/games/%s?locale=en-US", id
        );
        try {            
            JsonNode root = sendGet(url);
            root = root.get("_embedded");
            root = root.get("product");
            boolean isAvailable = root.get("isAvailableForSale").asBoolean();
            return isAvailable;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getGameTitle(String id){
        String url = String.format(
            "https://api.gog.com/v2/games/%s?locale=en-US", id
        );

        try {            
            JsonNode root = sendGet(url);
            root = root.get("_embedded");
            root = root.get("product");
            String title = root.get("title").asText();
            return title;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";

    }


    public double getGamePrice(String id){
        
        String url = String.format(
            "https://api.gog.com/products/%s/prices?countryCode=IE&currency=EUR", id
        );

        try {
            JsonNode root = sendGet(url);
            root = root.get("_embedded");
            JsonNode prices = root.path("prices");
            if(prices.isArray() && prices.size() > 0){
                for(JsonNode price : prices){
                    String gamePriceStr = price.path("basePrice").asText();
                    String numericPart = gamePriceStr.replaceAll("[^0-9]", "");
                    double gamePrice = Integer.parseInt(numericPart) / 100.0;
                    return gamePrice;
                }
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private JsonNode sendGet(String url) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

                    
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readTree(response.body());
    }

}

