package cellsociety;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Connor Penny*/

class CSVFileGeneratorTest {
    int width = 10;
    int height = 10;
    String[][] states = {{"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
        {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
        {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
        {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
        {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
        {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
        {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
        {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
        {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
        {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"}};
    RectangleGrid grid = new RectangleGrid("Rectangle", width, height, states, new GOLRules());
    private CSVFileGenerator fileGenerator = new CSVFileGenerator(grid, "Game of Life");

    @Test
    void createCSVFile() throws IOException {
        fileGenerator.createCSVFile("GOL");
        File generatedFile = new File("data\\SavedConfigs\\GameofLife.csv");

        assertEquals(true, generatedFile.isFile());

        CSVFileReader fileReader = new CSVFileReader("data\\SavedConfigs\\GameofLife.csv");
        assertEquals(height, fileReader.getHeight());
        assertEquals(width, fileReader.getWidth());

        for(int yPos = 0; yPos<height; yPos++) {
            for (int xPos = 0; xPos < width; xPos++) {
                assertEquals(states[yPos][xPos], fileReader.readStates()[yPos][xPos]);
            }
        }




    }
}