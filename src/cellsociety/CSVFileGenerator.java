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
        File savedConfig = new File("data\\SavedConfigs\\" + gameTypeNoSpaces + ".csv");
        FileWriter config = new FileWriter(savedConfig);
        config.append(gameType);
        config.append("\n");
        String strHeight = Integer.toString(height);
        String strWidth = Integer.toString(width);
        config.append(strHeight + ",");
        config.append(strWidth);
        config.append("\n");
        for(int yPos = 0; yPos<height; yPos++) {
            for (int xPos = 0; xPos < width; xPos++) {
                config.append(states[yPos][xPos]);
                if(xPos != width-1) {
                    config.append(",");
                }
            }
            if(yPos != width-1) {
                config.append("\n");
            }
        }

        config.flush();
        config.close();


    }
}
