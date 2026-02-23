package lab1.exception;

public abstract class LabInputException extends RuntimeException {
    private final String fixHint;

    protected LabInputException(String message, String fixHint) {
        super(message);
        this.fixHint = fixHint;
    }

    public String getFixHint() {
        return fixHint;
    }
}
