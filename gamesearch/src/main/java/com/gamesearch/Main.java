package com.gamesearch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {



    

    public static void main(String[] args) throws Exception {

    String apiKey = System.getenv("API_KEY");

    if(apiKey == null || apiKey.isEmpty()){
        throw new IllegalStateException("API_KEY enviornment variable is not set");
    }        

        String rawJsonString = """
                {
                  "title": "Dark Souls",
                  "price": 9.99,
                  "tags": ["SoulsLike"]
                }
                """;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(rawJsonString);

        // System.out.println("Successfully parsed rawJsonString object");
        // System.out.println(root.get("title").asText());
        // System.out.println(root.get("price").asDouble());
        // System.out.println(root.get("tags").get(0).asText());

        // System.out.println(root.toPrettyString());

        String url = String.format(
            "https://api.gg.deals/v1/prices/by-steam-app-id/?ids=570940&key=%s&region=us",apiKey
        );

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
                
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status code: " + response.statusCode());
            System.out.println("Body: ");
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }        
            


    }
}