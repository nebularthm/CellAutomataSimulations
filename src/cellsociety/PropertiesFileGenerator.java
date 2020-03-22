package cellsociety;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/***
 * Creates a properties file that holds important information for running a simulation such as the simulation type, the author, the description, the CSV file
 * for the simulation configuration, the title of the simulation, and the parameters of the simulation (if they exist)
 * @author Connor Penny
 */
public class PropertiesFileGenerator {

    private String gameType;
    private String author;
    private String Description;
    private String CSVFile;
    private String title;
    private String parameter;

    /***
     * Keeps the required information for the properties file that is passed to it as instance variables
     * @param configInfo a list of the information needed to create the properties file for a simulation
     */
    public PropertiesFileGenerator(List<String> configInfo) {
        title = configInfo.get(0);
        author = configInfo.get(1);
        Description = configInfo.get(2);
        CSVFile = configInfo.get(3);
        gameType = configInfo.get(4);
        parameter = ""; //Parameter not implemented yet
        //parameter = configInfo.get(5);
    }

    /***
     * Writes the properties file for a simulation with the information passed to the constructor
     * @param filename the name the properties file will be saved under
     * @throws IOException
     */
    public void createPropertiesFile(String filename) throws IOException {
        Properties props = new Properties();
        FileOutputStream fos = new FileOutputStream("src/cellsociety/Configurations/" + filename + ".properties");
        props.setProperty("GameType", gameType);
        props.setProperty("Title", title);
        props.setProperty("Author", author);
        props.setProperty("Description", Description);
        props.setProperty("CSVFile", CSVFile + ".csv");
        props.setProperty("Parameters", parameter);
        props.store(fos,"created properties file");
        fos.close();
    }

}
