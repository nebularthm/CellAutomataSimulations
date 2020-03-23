package cellsociety;

import java.io.IOException;
import java.util.ResourceBundle;

/***
 * Reads information for a Simulation that a User wishes to load into the GUI. Has methods to return this information so that they can
 * be displayed in the GUI
 *
 * @author Connor Penny
 */
public class PropertiesFileReader {
    private static final String PROP_FILE_START_IND = "cellsociety";
    private static final String PROP_FILE_END_IND = ".properties";
    private ResourceBundle myConfigFile;
    private ResourceBundle myExceptionBundle;

    /***
     * Accesses the properties file that is passed in as a parameter and saves it as instance variable. Also accesses the properties file that contains exception
     * error messages and saves it as an instance variable
     * @param filePath string file name for the simulation info being read
     */
    public PropertiesFileReader(String filePath) {
        filePath = filePath.replace("\\", ".");
        int startOfBundleName = filePath.indexOf(PROP_FILE_START_IND);
        int endOfBundleName = filePath.indexOf(PROP_FILE_END_IND);
        String resourceBundle = filePath.substring(startOfBundleName, endOfBundleName);
        myConfigFile = ResourceBundle.getBundle(resourceBundle);
        myExceptionBundle = ResourceBundle.getBundle("cellsociety.ExceptionResources.ConfigFileExceptionMessages");
    }

    /***
     * Reads the type of simulation, throws an exception if not valid
     * @return string simulation type
     */
    public String readGameType() {
        for(SimulationType x : SimulationType.values()) {
            if(x.equals(myConfigFile.getString("GameType"))) {
                return myConfigFile.getString("GameType");
            }
        }
        throw new ConfigurationFileException(myExceptionBundle.getString("GameTypeError"));

    }

    /***
     * Reads the title of simulation, throws an exception if nonexistent
     * @return string simulation title
     */
    public String readTitle() {
        if(keyExists("Title")) {
            return myConfigFile.getString("Title");
        }
        else {
            throw new ConfigurationFileException(myExceptionBundle.getString("TitleError"));
        }
    }

    /***
     * Reads the author of simulation, throws an exception if nonexistent
     * @return string simulation author
     */
    public String readAuthor() {
        if(keyExists("Author")) {
            return myConfigFile.getString("Author");
        }
        else {
            throw new ConfigurationFileException(myExceptionBundle.getString("AuthorError"));
        }
    }
    /***
     * Reads the description of simulation, throws an exception if nonexistent
     * @return string simulation description
     */
    public String readDescriptions() {
        if(keyExists("Description")) {
            return myConfigFile.getString("Description");
        }
        else {
            throw new ConfigurationFileException(myExceptionBundle.getString("DescriptionError"));
        }
    }

    /***
     * Reads the CSV of simulation, throws an exception if nonexistent
     * @return CSVFileReader object that can access configuration info
     */
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
    /***
     * Reads the parameters of simulation
     * @return integer parameter, or 2 if no parameter is given
     */
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
