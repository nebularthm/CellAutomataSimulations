package cellsociety;

public class ConfigurationFileException extends RuntimeException {
    public ConfigurationFileException() {

    }

    public ConfigurationFileException(String message) { super(message); }

    public ConfigurationFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
