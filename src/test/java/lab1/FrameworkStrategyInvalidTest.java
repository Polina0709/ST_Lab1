package lab1;

import lab1.exception.InvalidLineException;
import lab1.exception.OutOfRangeException;
import lab1.validation.LineConstraintsValidator;
import lab1.validation.RangeValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrameworkStrategyInvalidTest {

    // Неприпустимий клас: поза діапазоном
    @ParameterizedTest
    @ValueSource(ints = {120, -130, 1000})
    void invalid_outOfRange_throws(int v) {
        assertThrows(OutOfRangeException.class, () -> RangeValidator.requireInRange(v));
    }

    // Неприпустимий клас: L1 (a1=b1=0)
    @ParameterizedTest
    @ValueSource(ints = {0, 0, 0})
    void invalid_L1_a_b_zero(int ignored) {
        assertThrows(InvalidLineException.class, () -> LineConstraintsValidator.validateGeneral(0, 0));
    }

    // Неприпустимий клас: L2 (a2=0 або b2=0) — 3 приклади
    @ParameterizedTest
    @ValueSource(ints = {0, 0, 0})
    void invalid_L2_zeroParams(int ignored) {
        assertThrows(InvalidLineException.class, () -> LineConstraintsValidator.validateIntercepts(0, 2));
        assertThrows(InvalidLineException.class, () -> LineConstraintsValidator.validateIntercepts(2, 0));
        assertThrows(InvalidLineException.class, () -> LineConstraintsValidator.validateIntercepts(0, -5));
    }

    // Неприпустимий клас: L3 (a3=b3=0)
    @ParameterizedTest
    @ValueSource(ints = {0, 0, 0})
    void invalid_L3_a_b_zero(int ignored) {
        assertThrows(InvalidLineException.class, () -> LineConstraintsValidator.validatePointNormal(0, 0));
    }
}