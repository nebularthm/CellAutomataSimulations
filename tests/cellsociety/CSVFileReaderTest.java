package cellsociety;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CSVFileReaderTest {
    private CSVFileReader myFileReader = new CSVFileReader("Data/GameOfLife.csv");
    private static final String GOL = "Game of Life";
    CSVFileReaderTest() throws IOException {
    }

    @Test
    void readGame() throws IOException {
        assertEquals(GOL,myFileReader.readGame());
    }

    @Test
    void getHeight() throws IOException {
        assertEquals(10,myFileReader.getHeight());
    }

    @Test
    void getWidth() throws IOException {
        assertEquals(10,myFileReader.getWidth());
    }

    @Test
    void readStates() throws IOException {
        String [][] init = myFileReader.readStates();
        assertEquals(init.length,10);
        assertEquals(init[0].length,10);
    }
}