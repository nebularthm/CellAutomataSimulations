package cellsociety;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Connor Penny*/

class CSVFileGeneratorTest {
    private CSVFileGenerator fileGenerator = new CSVFileGenerator();

    @Test
    void createCSVFile() throws IOException {
        String gameName = "Game of Life";
        int width = 10;
        int height = 10;
        String[][] states = new String[height][width];
        states = new String[][]{{"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
                    {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
                    {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
                    {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
                    {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
                    {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
                    {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
                    {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
                    {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"},
                    {"dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead"}};
        fileGenerator.createCSVFile(gameName, height, width, states);
        File generatedFile = new File("data\\GameofLife.csv");
        assertEquals(true, generatedFile.isFile());

        CSVFileReader fileReader = new CSVFileReader("data\\GameofLife.csv");
        assertEquals(height, fileReader.getHeight());
        assertEquals(width, fileReader.getWidth());
        assertTrue(states.equals(fileReader.readStates()));


    }
}