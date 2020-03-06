package cellsociety;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesFileGeneratorTest {

    private String filename = "GOLBlinker";
    private ResourceBundle GOLResourceBundle;
    private List<String> config;

    @Test
    void createPropertiesFileTest() throws IOException {
        config = new ArrayList<String>();
        config.add("Game of Life");
        config.add("Blinker");
        config.add("Connor Penny");
        config.add("A 10x10 grid of rectangles with 3 alive states that form a horizontal blinker");
        config.add("data/GameOfLife1.csv");
        config.add("property");
        PropertiesFileGenerator pfg = new PropertiesFileGenerator(config);
        GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Configurations.GOLBlinker");
        pfg.createPropertiesFile("GOLBlinker");
        assertEquals(GOLResourceBundle.getString("GameType"),"Game of Life");
        assertEquals(GOLResourceBundle.getString("Title"),"data/GameOfLife1.csv");
        assertEquals(GOLResourceBundle.getString("Author"),"Blinker");
        assertEquals(GOLResourceBundle.getString("Description"),"Connor Penny");
        assertEquals(GOLResourceBundle.getString("CSVFile"),"A 10x10 grid of rectangles with 3 alive states that form a horizontal blinker.csv");
    }

}