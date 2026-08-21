package com.gamesearch;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;

public class HltbApiTest {


    @Test
    public void sendGet_validUrl_returnsPopulatedOptinal() throws Exception{
        //Arrange
        String validUrl = "https://hltbapi.codepotatoes.de/steam/1903340";
        HltbApi api = new HltbApi();
        //Act
        Optional<JsonNode> result = api.sendGet(validUrl);
        assertTrue(result.isPresent());
    }
    
    @Test
    public void sendGet_invalidUrl_returnsEmptyOptional() throws Exception{
        //Arrange
        HltbApi api = new HltbApi();
        String invalidUrl = "https://hltbapi.codepotatoes.de/steam/xxx";
        //Act
        Optional<JsonNode> result = api.sendGet(invalidUrl);
        assertTrue(result.isEmpty()); 
    }


    @Test
    public void getTime_validId_returnsMainStoryTime(){
        //Arrange
        HltbApi api = new HltbApi();
        String validId = "1903340";
        //Act
        String result = api.getTime(validId);
        assertTrue(!result.equals("Undefined"));
    }

    @Test
    public void getTime_invalidId_returnsUndefined(){
        //Arrange
        HltbApi api = new HltbApi();
        String invalidId = "xx";
        //Act
        String result = api.getTime(invalidId);
        assertTrue(result.equals("Undefined"));
    }




}
