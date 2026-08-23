package com.gamesearch;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SteamApi {

    
    private final HttpClient client;
    private final JsonRequestBuilder jsonRequestBuilder;

    static ObjectMapper mapper = new ObjectMapper();

    public SteamApi(){
        client = HttpClient.newBuilder().build();
        jsonRequestBuilder = new JsonRequestBuilder();
    }

    public Map<String, String> getId(String keyword){
        if(keyword == null || keyword.isEmpty()){
            throw new IllegalStateException("No keyword entered");
        }
        
        Map<String, String> ids = new HashMap<>();

        String url = String.format(
            "https://store.steampowered.com/api/storesearch/?term=%s&1=english&cc=US&charset=utf-8", encodeValue(keyword)
        );

        HttpRequest request = jsonRequestBuilder.buildRequest(url);

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            JsonNode root = mapper.readTree(response.body());

            ids = buildIdMap(root);

        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return ids;
          
    }

    public Map<String, String> buildIdMap(JsonNode root){
        Map<String, String> ids = new HashMap<>();
        JsonNode items = root.path("items");
            if(items.isArray() && items.size() > 0){
                for(JsonNode item : items){
                    String id = item.path("id").asText();
                    String name = item.path("name").asText();
                    
                    ids.put(id, name);
                }
            }
        return ids;        
    }

    public boolean isUnrealeased(String id){
        String url = String.format(
            "https://store.steampowered.com/api/appdetails?appids=%s" ,id
        );

        HttpRequest request = jsonRequestBuilder.buildRequest(url);

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            JsonNode root = mapper.readTree(response.body());

            root = root.get(id);
            root = root.get("data");
            root = root.get("release_date");
            boolean comingSoon = root.get("coming_soon").asBoolean();
            return comingSoon;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;                    
    }


    private String encodeValue(String value) {
    try {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return null;
}
    
}
