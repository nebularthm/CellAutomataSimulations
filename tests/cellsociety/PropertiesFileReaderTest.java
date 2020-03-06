package cellsociety;

import org.junit.jupiter.api.Test;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesFileReaderTest {

    PropertiesFileReader pfr = new PropertiesFileReader("cellsociety.Configurations.Test.properties");
    PropertiesFileReader pfr2 = new PropertiesFileReader("cellsociety.Configurations.GOLBlinker.properties");

    @Test
    void readGameTypeTest() {
        assertEquals(pfr2.readGameType(),"Game of Life");
        assertThrows(MissingResourceException.class, () -> pfr.readGameType());
    }

    @Test
    void readTitleTest() {
        assertEquals(pfr2.readTitle(),"data/GameOfLife1.csv");
        assertThrows(ConfigurationFileException.class, () -> pfr.readTitle());

    }

    @Test
    void readAuthorTest() {
        assertEquals(pfr2.readAuthor(),"Blinker");
        assertThrows(ConfigurationFileException.class, () -> pfr.readAuthor());

    }

    @Test
    void readDescriptionsTest() {
        assertEquals(pfr2.readDescriptions(),"Connor Penny");
        assertThrows(ConfigurationFileException.class, () -> pfr.readDescriptions());

    }

    @Test
    void readCSVFileTest() {
        Exception error = assertThrows(ConfigurationFileException.class, () -> pfr.readCSVFile());
        assertEquals(error.getMessage(),"No CSV File Found");
    }
}