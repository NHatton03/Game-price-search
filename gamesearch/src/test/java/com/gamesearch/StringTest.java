package com.gamesearch;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringTest {

    @Test
    public void testCreateIdsString(){
        //Arrange
        GGDealsApi api = new GGDealsApi("Dummy Key");
        //Act
        var actual = api.createIdsString(new String[]{});
        var expected = "";

        assertEquals(expected, actual);

        actual = api.createIdsString(new String[]{null});
        expected = "null";

        assertEquals(expected, actual);

        actual = api.createIdsString(new String[]{"test"});
        expected = "test";

        assertEquals(expected, actual);

        actual = api.createIdsString(new String[]{"one", "two"});
        expected = "one,two";

        assertEquals(expected, actual);
    }
}
