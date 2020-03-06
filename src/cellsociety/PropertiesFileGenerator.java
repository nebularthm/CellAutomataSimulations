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
    private String parameter;

    public PropertiesFileGenerator(List<String> configInfo) {
        title = configInfo.get(0);
        author = configInfo.get(1);
        Description = configInfo.get(2);
        CSVFile = configInfo.get(3);
        gameType = configInfo.get(4);
        parameter = "";
        //parameter = configInfo.get(5);
    }

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
