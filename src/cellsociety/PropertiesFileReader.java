package cellsociety;

import java.io.IOException;
import java.util.ResourceBundle;

public class PropertiesFileReader {
    private static final String PROP_FILE_START_IND = "cellsociety";
    private static final String PROP_FILE_END_IND = ".properties";
    private ResourceBundle myConfigFile;
    private ResourceBundle myExceptionBundle;

    public PropertiesFileReader(String filePath) {
        filePath = filePath.replace("\\", ".");
        int startOfBundleName = filePath.indexOf(PROP_FILE_START_IND);
        int endOfBundleName = filePath.indexOf(PROP_FILE_END_IND);
        String resourceBundle = filePath.substring(startOfBundleName, endOfBundleName);
        myConfigFile = ResourceBundle.getBundle(resourceBundle);
        myExceptionBundle = ResourceBundle.getBundle("cellsociety.ExceptionResources.ConfigFileExceptionMessages");
    }

    public String readGameType() {
        for(SimulationType x : SimulationType.values()) {
            if(x.equals(myConfigFile.getString("GameType"))) {
                return myConfigFile.getString("GameType");
            }
        }
        throw new ConfigurationFileException(myExceptionBundle.getString("GameTypeError"));

    }

    public String readTitle() {
        if(keyExists("Title")) {
            return myConfigFile.getString("Title");
        }
        else {
            throw new ConfigurationFileException(myExceptionBundle.getString("TitleError"));
        }
    }

    public String readAuthor() {
        if(keyExists("Author")) {
            return myConfigFile.getString("Author");
        }
        else {
            throw new ConfigurationFileException(myExceptionBundle.getString("AuthorError"));
        }
    }

    public String readDescriptions() {
        if(keyExists("Description")) {
            return myConfigFile.getString("Description");
        }
        else {
            throw new ConfigurationFileException(myExceptionBundle.getString("DescriptionError"));
        }
    }

    public CSVFileReader readCSVFile() {
        if(keyExists("CSVFile")) {
            String strCSVFile = myConfigFile.getString("CSVFile");
            CSVFileReader reader = new CSVFileReader(strCSVFile);
            return reader;
        }
        else {
            throw new ConfigurationFileException(myExceptionBundle.getString("CSVFileError"));
        }

    }

    public int readParameters() {
        if(myConfigFile.containsKey("Parameters")) {
            String param = myConfigFile.getString("Parameters");
            return Integer.parseInt(param);
        }
        return 2;
    }

    private boolean keyExists(String key) {
        return myConfigFile.containsKey(key);
    }
}
