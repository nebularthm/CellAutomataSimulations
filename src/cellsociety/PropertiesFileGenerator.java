package cellsociety;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileGenerator {

    private String gameType;
    private String author;
    private String Description;
    private String CSVFile;

    public PropertiesFileGenerator(Grid grid, String type) {
        gameType = type;
        author = "Team 5";
        Description = "";
        CSVFile = "";
    }

    public void createPropertiesFile(String filename) throws IOException {
        Properties props = new Properties();
        FileOutputStream fos = new FileOutputStream("cellsociety.Resources");
        props.setProperty("GameType", gameType);
        props.setProperty("Title", gameType);
        props.setProperty("Author", author);
        props.setProperty("Description", Description);
        props.setProperty("CSVFile", CSVFile);
        props.store(fos,"created properties file");
        fos.close();
    }

}
