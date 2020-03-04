package cellsociety;

import java.io.IOException;
import java.util.ResourceBundle;

public class PropertiesFileReader {
    private static final String PROP_FILE_START_IND = "cellsociety";
    private static final String PROP_FILE_END_IND = ".properties";
    private ResourceBundle myConfigFile;



    public PropertiesFileReader(String filePath) {
        filePath = filePath.replace("\\", ".");
        int startOfBundleName = filePath.indexOf(PROP_FILE_START_IND);
        int endOfBundleName = filePath.indexOf(PROP_FILE_END_IND);
        String resourceBundle = filePath.substring(startOfBundleName, endOfBundleName);
        myConfigFile = ResourceBundle.getBundle(resourceBundle);
    }

    public String readGameType() {
        return myConfigFile.getString("GameType");
    }

    public String readTitle() {
        return myConfigFile.getString("Title");
    }

    public String readAuthor() {
        return myConfigFile.getString("Author");
    }

    public String readDescriptions() {
        return myConfigFile.getString("Description");
    }

    /*public Simulate getInitializedSimulation() throws IOException {
        String strCSVFile = myConfigFile.getString("CSVFile");
        CSVFileReader reader = new CSVFileReader(strCSVFile);
        Simulate simulation = new Simulate(reader, readGameType());
        return simulation;
    }*/

    public CSVFileReader readCSVFile() {
        String strCSVFile = myConfigFile.getString("CSVFile");
        CSVFileReader reader = new CSVFileReader(strCSVFile);
        return reader;
    }

    public int readParameters() {
        if(myConfigFile.containsKey("Parameters")) {
            String param = myConfigFile.getString("Parameters");
            return Integer.parseInt(param);
        }
        return 2;
    }
}
