package com.gamesearch;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("StringTest")
public class StringTest {

    static Stream<Arguments>provideTestCases(){
        return Stream.of(
          Arguments.of(new String[]{}, ""),
          Arguments.of(new String[]{"test"}, "test"),
          Arguments.of(new String[]{"one", "two"}, "one,two")   
        );
    }
    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testCreateIdsString(String[] actual, String expected){
        //Arrange
        GGDealsApi api = new GGDealsApi("Dummy Key");
        //Act
        assertEquals(expected, api.createIdsString(actual));
        

       
    }
}
