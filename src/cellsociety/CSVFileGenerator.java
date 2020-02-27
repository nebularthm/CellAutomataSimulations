package cellsociety;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Connor Penny*/
public class CSVFileGenerator {

    public CSVFileGenerator() {

    }

    public void createCSVFile(String gameType, int height, int width, String[][] states) throws IOException {
        String gameTypeNoSpaces = gameType.replace(" ", "");
        File savedConfig = new File("data\\" + gameTypeNoSpaces + ".csv");
        FileWriter config = new FileWriter(savedConfig);
        config.append(gameType);
        config.append("\n");
        config.append((char) height);
        config.append((char) width);
        config.append("\n");
        for(int yPos = 0; yPos<height; yPos++) {
            for (int xPos = 0; xPos < height; xPos++) {
                config.append(states[yPos][xPos]);
            }
            config.append("\n");
        }

        config.flush();
        config.close();


    }
}
