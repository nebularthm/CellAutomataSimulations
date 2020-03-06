package cellsociety;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PropertiesFileGenerator {

    private String gameType;
    private String author;
    private String Description;
    private String CSVFile;
    private String title;

    public PropertiesFileGenerator(List<String> configInfo) {
        gameType = configInfo.get(0);
        author = configInfo.get(1);
        Description = configInfo.get(2);
        CSVFile = configInfo.get(3);
        title = configInfo.get(4);
    }

    public void createPropertiesFile(String filename) throws IOException {
        Properties props = new Properties();
        FileOutputStream fos = new FileOutputStream("src\\cellsociety\\SavedConfigurations\\" + filename + ".properties");
        props.setProperty("GameType", gameType);
        props.setProperty("Title", title);
        props.setProperty("Author", author);
        props.setProperty("Description", Description);
        props.setProperty("CSVFile", CSVFile);
        props.store(fos,"created properties file");
        fos.close();
    }

}
