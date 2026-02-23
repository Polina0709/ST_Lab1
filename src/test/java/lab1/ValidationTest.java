package lab1;

import lab1.exception.InvalidLineException;
import lab1.exception.OutOfRangeException;
import lab1.validation.LineConstraintsValidator;
import lab1.validation.RangeValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {

    @Test
    void rangeValidator_outOfRange_throws() {
        assertThrows(OutOfRangeException.class, () -> RangeValidator.requireInRange(120));
        assertThrows(OutOfRangeException.class, () -> RangeValidator.requireInRange(-130));
    }

    @Test
    void generalLine_aAndBZero_throws() {
        assertThrows(InvalidLineException.class, () -> LineConstraintsValidator.validateGeneral(0, 0));
    }

    @Test
    void intercepts_aOrBZero_throws() {
        assertThrows(InvalidLineException.class, () -> LineConstraintsValidator.validateIntercepts(0, 2));
        assertThrows(InvalidLineException.class, () -> LineConstraintsValidator.validateIntercepts(2, 0));
    }

    @Test
    void pointNormal_aAndBZero_throws() {
        assertThrows(InvalidLineException.class, () -> LineConstraintsValidator.validatePointNormal(0, 0));
    }
}