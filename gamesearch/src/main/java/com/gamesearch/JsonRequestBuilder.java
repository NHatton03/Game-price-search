package com.gamesearch;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class JsonRequestBuilder {
    
    public HttpRequest buildRequest(String url){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json; charset=UTF-8")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
    }
}
