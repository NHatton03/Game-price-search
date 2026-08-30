package com.gamesearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("SteamApiTest")
public class SteamApiTest {
    
    static String testData;
    static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getValidTestData() throws IOException{
        return mapper.readTree(Files.readString(Paths.get("src", "main", "resources", "ValidJson.json")));
    }

    public static JsonNode getInvalidTestData() throws IOException{
     return mapper.readTree(Files.readString(Paths.get("src", "main", "resources", "InvalidJson.json")));   
    }

    @Test
    public void buildIdMap_validJsonNode_PopulatedIdMap() throws IOException{
        //Arrange
        SteamApi api = new SteamApi();
        JsonNode root = getValidTestData();
        //Act
        Map<String, String> result = api.buildIdMap(root);
        //Assert
        assertEquals(10, result.size());
    }

    @Test
    public void buildIdMap_InvalidJsonNode_EmptyIdMap() throws IOException{
        //Arrange
        SteamApi api = new SteamApi();
        JsonNode root = getInvalidTestData();
        //Act
        Map<String, String> result = api.buildIdMap(root);
        //Assert
        assertEquals(0, result.size());
    }

    @Test
    public void isUnreleased_ValidId_False() throws IOException{
        //Arrange
        SteamApi api = new SteamApi();
        String validId = "400";
        //Act
        Boolean result = api.isUnrealeased(validId);
        //Assert
        assertFalse(result);
    }

    @Test
    public void isUnreleased_InvalidId_False() throws IOException{
        //Arrange
        SteamApi api = new SteamApi();
        String InvalidId = "xx";
        //Act
        Boolean result = api.isUnrealeased(InvalidId);
        //
        assertFalse(result);
    }

    


}
