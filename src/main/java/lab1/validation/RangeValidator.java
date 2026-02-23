package lab1.validation;

import lab1.exception.OutOfRangeException;

public final class RangeValidator {
    public static final int N = 19;
    public static final int MIN = -100 - N; // -119
    public static final int MAX =  100 + N; //  119

    private RangeValidator() {}

    public static int requireInRange(int value) {
        if (value < MIN || value > MAX) {
            throw new OutOfRangeException(value, MIN, MAX);
        }
        return value;
    }
}