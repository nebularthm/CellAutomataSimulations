package cellsociety;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Connor Penny*/
public class CSVFileGenerator {
    private String gameType;
    private int height;
    private int width;
    private Grid states;

    public CSVFileGenerator(Grid grid, String type) {
        gameType = type;
        height = grid.getGridHeight();
        width = grid.getGridWidth();
        states = grid;

    }

    public void createCSVFile(String filename) throws IOException {
        String gameTypeNoSpaces = gameType.replace(" ", "");
        File savedConfig = new File("data\\SavedConfigs\\" + filename + ".csv");
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
                config.append(states.getState(xPos,yPos));
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
