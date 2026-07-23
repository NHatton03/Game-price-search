package com.gamesearch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SteamApi {

    private final String keyword;
    private final HttpClient client;

    static ObjectMapper mapper = new ObjectMapper();

    public SteamApi(String keyword){
        if(keyword == null || keyword.isEmpty()){
            throw new IllegalStateException("No keyword entered");
        }
        this.keyword = keyword;
        client = HttpClient.newBuilder().build();
    }

    public String getId(){
        String url = String.format(
            "https://store.steampowered.com/api/storesearch/?term=%s&1=english&cc=US", keyword
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode root = mapper.readTree(response.body());
            
            JsonNode items = root.path("items");
            if(items.isArray() && items.size() > 0){
                for(JsonNode item : items){
                    String id = item.path("id").asText();
                    String name = item.path("name").asText();
                    System.out.println(id + ": " + name);
                }
            }else {
                System.out.println("No entry found");
            }
            
            
            
              

        } catch (Exception e) {
        }   
         return null;  
    }
    
}
