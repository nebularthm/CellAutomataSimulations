package cellsociety;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CSVFileReader {
    private String fileName;
    String csvSplitBy = ",";

    public CSVFileReader() throws IOException {

    }

    public void setFileName(String csvFile) {
        fileName = csvFile;
    }

    private String[] readDimension() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        line = br.readLine();
        String[] dimensions = line.split(csvSplitBy);

        return dimensions;
    }

    public int getHeight() throws IOException {
        String[] dimensions = readDimension();
        return Integer.parseInt(dimensions[0]);
    }

    public int getWidth() throws IOException {
        String[] dimensions = readDimension();
        return Integer.parseInt(dimensions[1]);
    }

    public List<List> readStates() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        br.readLine();
        String line;
        List states = null;
        while ((line = br.readLine()) != null) {
            String[] row = line.split(csvSplitBy);
            List<String> rowStates = Arrays.asList(row);
            states.add(rowStates);
        }
        return states;
    }

}
