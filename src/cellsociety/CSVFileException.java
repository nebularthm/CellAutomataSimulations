package cellsociety;

public class CSVFileException extends RuntimeException {
    public CSVFileException() {

    }

    public CSVFileException(String message) { super(message); }

    public CSVFileException(String message, Throwable cause) {
        super(message, cause);
    }
}


