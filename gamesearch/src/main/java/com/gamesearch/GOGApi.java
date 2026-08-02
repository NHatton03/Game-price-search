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


    public void getGameTitle(String id){
        String url = String.format(
            "https://api.gog.com/v2/games/%s?locale=en-US", id
        );

        try {            
            JsonNode root = sendGet(url);
            root = root.get("_embedded");
            root = root.get("product");
            String title = root.get("title").asText();
            System.out.println(title);
        }catch(Exception e){
            e.printStackTrace();
    }


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

