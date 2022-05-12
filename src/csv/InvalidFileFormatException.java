package csv;

/**
 * Creates and returns an error message for when there is an invalid file format error
 *
 * @author Foothill College, Tanvi Waghela
 */
public class InvalidFileFormatException extends java.io.IOException{
    private String message;
    private String filename;

    /**
     * Creates a new InvalidFileFormatException abject
     * @param message  The error message the user puts
     * @param filename  The name of the file
     */
    public InvalidFileFormatException(String message, String filename){
        this.message = message;
        this.filename = filename;
    }

    /**
     * Creates and returns the error message to the user
     * @return  returns the error message to the user
     */
    public String getMessage(){
        return filename + ". Expecting " + message;
    }
}

