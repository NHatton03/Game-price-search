package com.gamesearch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
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

            return Optional.of(extractGameFromJson(response.body(), id));
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return Optional.empty();   
    }

    private Game extractGameFromJson(String responseBody ,String id) throws Exception{
        JsonNode root = mapper.readTree(responseBody);
        root = root.get("data");
        root = root.get(id);
        String title = root.get("title").asText();
        root = root.get("prices");
        String price = root.get("currentRetail").asText();
        return new Game(title, price);
    }
}
