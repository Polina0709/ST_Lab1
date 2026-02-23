package lab1.exception;

public final class OutOfRangeException extends LabInputException {
    public OutOfRangeException(int value, int min, int max) {
        super(
                "Значення " + value + " виходить за допустимий діапазон [" + min + ".." + max + "].",
                "Введіть ціле число в межах [" + min + ".." + max + "]."
        );
    }
}