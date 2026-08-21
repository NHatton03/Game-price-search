package com.gamesearch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HltbApi {
    

    private final HttpClient client;
    static ObjectMapper mapper = new ObjectMapper();

    public HltbApi(){
        client = HttpClient.newBuilder().build();
    }

    public String getTime(String id){
        String url = String.format(
            "https://hltbapi.codepotatoes.de/steam/%s", id
        );

        try{
            Optional<JsonNode> optRoot = sendGet(url);
            if(optRoot.isPresent()){
            String mainStoryTime = optRoot.get().get("mainStory").asText();
            if(mainStoryTime == null || mainStoryTime.equals("null")){
                return "Undefined";
            }
            return mainStoryTime;
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }

        return "Undefined";
    }




    public Optional<JsonNode> sendGet(String url) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        //https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status#successful_responses
        if(response.statusCode() >= 200 && response.statusCode() <= 299){
            return Optional.ofNullable(mapper.readTree(response.body()));
        }
        return Optional.empty();

    }


}
