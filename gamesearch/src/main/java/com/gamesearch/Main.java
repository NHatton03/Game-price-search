package com.gamesearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws Exception {

        String rawJsonString = """
                {
                  "title": "Dark Souls",
                  "price": 9.99,
                  "tags": ["SoulsLike"]
                }
                """;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(rawJsonString);

        System.out.println("Successfully parsed rawJsonString object");
        System.out.println(root.get("title").asText());
        System.out.println(root.get("price").asDouble());
        System.out.println(root.get("tags").get(0).asText());

        System.out.println(root.toPrettyString());
    }
}