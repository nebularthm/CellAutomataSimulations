package cellsociety;

/***
 * A class for exceptions that are thrown when reading a CSV file
 * @author Connor Penny
 */

public class CSVFileException extends RuntimeException {
    public CSVFileException() {

    }

    /***
     * Constructs the exception that will be thrown in CSVFileReader
     * @param message the message for the particular error that is occurring
     */
    public CSVFileException(String message) { super(message); }

    /***
     * Constructs the exception that will be thrown in CSVFileReader
     * @param message the message for the particular error that is occurring
     * @param cause the cause of the error
     */
    public CSVFileException(String message, Throwable cause) {
        super(message, cause);
    }
}


