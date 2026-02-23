package lab1.exception;

public final class InvalidLineException extends LabInputException {
    public InvalidLineException(String message, String fixHint) {
        super(message, fixHint);
    }
}