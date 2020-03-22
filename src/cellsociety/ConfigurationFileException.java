package cellsociety;

/***
 * A class for exceptions that are thrown when reading a properties file
 * @author Connor Penny
 */

public class ConfigurationFileException extends RuntimeException {
    public ConfigurationFileException() {

    }

    /***
     * Constructs the exception that will be thrown in PropertiesFileReader
     * @param message the message for the particular error that is occurring
     */
    public ConfigurationFileException(String message) { super(message); }

    /***
     * Constructs the exception that will be thrown in PropertiesFileReader
     * @param message the message for the particular error that is occurring
     * @param cause the cause of the error
     */
    public ConfigurationFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
