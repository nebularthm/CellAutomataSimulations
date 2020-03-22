package cellsociety;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/***
 * Writes a CSV File with a simulation's height, width, and comma delimited states
 * @author Connor Penny
 * */
public class CSVFileGenerator {
    private String gameType;
    private int height;
    private int width;
    private Grid states;

    /***
     * Holds the height, width, and grid object of a simulation's grid object as instance variables
     * @param grid a simulation's grid object
     */
    public CSVFileGenerator(Grid grid) {
        height = grid.getGridHeight();
        width = grid.getGridWidth();
        states = grid;

    }

    /***
     * Writes a CSV file with the proper format to be compatible with the CSVFileReader class
     * @param filename the file name that the CSV file will be saved under
     * @throws IOException
     */
    public void createCSVFile(String filename) throws IOException {
        File savedConfig = new File("data\\SavedConfigs\\" + filename + ".csv");
        FileWriter config = new FileWriter(savedConfig);
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
