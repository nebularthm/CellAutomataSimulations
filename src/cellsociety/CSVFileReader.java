package cellsociety;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/***
 * Reads a CSV file to obtain the height, width, and initial states of any simulation
 * @author Connor Penny
 */
public class CSVFileReader {
    private String fileName;
    String csvSplitBy = ",";
    ResourceBundle myExceptionBundle;

    /***
     * Constructor keeps file name and the properties file for exception messages as an instance variable
     * @param csvFile the name of the CSV file for a simulation
     */
    public CSVFileReader(String csvFile) {
        fileName = csvFile;
        myExceptionBundle = ResourceBundle.getBundle("cellsociety.ExceptionResources.CSVFileExceptionMessages");
    }


    private String[] readDimension() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        String[] dimensions = line.split(csvSplitBy);

        return dimensions;
    }

    /***
     * Reads the height of the simulation grid from the CSV file
     * @return the height of the simulation grid
     * @throws IOException
     */
    public int getHeight() throws IOException {
        String[] dimensions = readDimension();
        return Integer.parseInt(dimensions[0]);
    }
    /***
     * Reads the width of the simulation grid from the CSV file
     * @return the width of the simulation grid
     * @throws IOException
     */
    public int getWidth() throws IOException {
        String[] dimensions = readDimension();
        return Integer.parseInt(dimensions[1]);
    }

    /***
     * Reads the states of the simulation grid from the CSV file
     * @return a 2D array of the states of the simulation grid
     * @throws IOException
     */
    public String[][] readStates() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        br.readLine();
        String line;
        String[][] states = new String[getHeight()][getWidth()];
        int currRow = 0;//make it -1 to skip over the first line
        while ((line = br.readLine()) != null) {
            String[] row = line.split(csvSplitBy);
            if (row.length != getWidth()) {
                throw new CSVFileException(myExceptionBundle.getString("DimensionError"));
            }
            states[currRow] = row;
            currRow += 1;
        }

        if(currRow != getHeight()) {
            throw new CSVFileException(myExceptionBundle.getString("DimensionError"));
        }
        return states;
    }

}
