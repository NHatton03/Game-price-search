package com.gamesearch;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HltbApi {
    

    private final HttpClient client;
    private final JsonRequestBuilder jsonRequestBuilder;
    static ObjectMapper mapper = new ObjectMapper();

    public HltbApi(){
        client = HttpClient.newBuilder().build();
        jsonRequestBuilder = new JsonRequestBuilder();
    }

    public String getTime(String id) throws Exception{
        String url = String.format(
            "https://hltbapi.codepotatoes.de/steam/%s", id
        );

            Optional<JsonNode> optRoot = sendGet(url);
            if(optRoot.isPresent()){
            String mainStoryTime = optRoot.get().get("mainStory").asText();
            if(mainStoryTime == null || mainStoryTime.equals("null")){
                return "Undefined";
            }
            return mainStoryTime;
            }


        return "Undefined";
    }




    public Optional<JsonNode> sendGet(String url) throws Exception{
        HttpRequest request = jsonRequestBuilder.buildRequest(url);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        //https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status#successful_responses
        if(response.statusCode() >= 200 && response.statusCode() <= 299){
            return Optional.ofNullable(mapper.readTree(response.body()));
        }
        return Optional.empty();

    }


}
