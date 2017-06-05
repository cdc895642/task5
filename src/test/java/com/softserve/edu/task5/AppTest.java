package com.softserve.edu.task5;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by cdc89 on 05.06.2017.
 */
public class AppTest {
    @Test
    public void Main_NoParams_PrintRules() throws IOException {
        //Arrange
        String[] args = new String[]{};
        final String RULES = "input integer value as an argument of the "
                + "program";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        App.setOut(new PrintStream(outContent));

        //Act
        App.main(args);

        //Assert
        assertEquals(RULES, outContent.toString());
        outContent.close();
    }
}
