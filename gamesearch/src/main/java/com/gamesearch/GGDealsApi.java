package com.gamesearch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GGDealsApi {

    private final String apiKey;
    private final HttpClient client;
    
    static ObjectMapper mapper = new ObjectMapper();
    public GGDealsApi(String apiKey){
        if(apiKey == null || apiKey.isEmpty()){
        throw new IllegalStateException("Invalid API key");
    }    
        this.apiKey = apiKey;
        client = HttpClient.newBuilder().build();
    }

    


    public Optional<Game> getGame(String id){
        String url = String.format(
            "https://api.gg.deals/v1/prices/by-steam-app-id/?ids=%s&key=%s&region=ie",id, apiKey
        );


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
                
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return Optional.ofNullable(extractGameFromJson(response.body(), id));
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return Optional.empty();   
    }

    private Game extractGameFromJson(String responseBody ,String id) throws Exception{
        JsonNode root = mapper.readTree(responseBody);
        root = root.get("data");
        root = root.get(id);
        

        //System.out.println(root.toPrettyString());
        JsonNode gameJson = root.get("title");
        if(gameJson == null){
            return null;
        }
        String title = gameJson.asText();
        root = root.get("prices");
        String priceStr = root.get("currentRetail").asText();
        if(priceStr == null || priceStr.equals("null")){
            return new SteamGame(title, 0, ReleaseStatus.Unreleased, id);
        }
        double price = root.get("currentRetail").asDouble();    
        return new SteamGame(title, price, ReleaseStatus.Released, id);
    }

    public List<Game> getGames(String[] ids){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ids.length; i++){
            sb.append(ids[i]);
            if(i+1 != ids.length){
                sb.append(",");
            }   
        }
        String url = String.format(
            "https://api.gg.deals/v1/prices/by-steam-app-id/?ids=%s&key=%s&region=ie",sb.toString(), apiKey
        );


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
                
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ArrayList<Game> games = new ArrayList<>();

            for(String id : ids){
                var game = extractGameFromJson(response.body(), id);
                if(game == null){
                    continue;
                }
                games.add(game);
            }

            Collections.sort(games);

            return games;
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return new ArrayList<>();
    }
}
