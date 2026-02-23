package lab1.validation;

import lab1.exception.InvalidLineException;

public final class LineConstraintsValidator {
    private LineConstraintsValidator() {}

    public static void validateGeneral(int a1, int b1) {
        if (a1 == 0 && b1 == 0) {
            throw new InvalidLineException(
                    "Некоректна пряма L1: a1=0 і b1=0 (заборонено, бо a1^2 + b1^2 повинно бути ≠ 0).",
                    "Задайте хоча б один з коефіцієнтів a1 або b1 ненульовим."
            );
        }
    }

    public static void validateIntercepts(int a2, int b2) {
        if (a2 == 0 || b2 == 0) {
            throw new InvalidLineException(
                    "Некоректна пряма L2 у відрізках: a2 та b2 повинні бути ≠ 0.",
                    "Введіть ненульові a2 і b2 (наприклад 2 та -3)."
            );
        }
    }

    public static void validatePointNormal(int a3, int b3) {
        if (a3 == 0 && b3 == 0) {
            throw new InvalidLineException(
                    "Некоректна пряма L3: a3=0 і b3=0 (вектор (a3,b3) не може бути нульовим).",
                    "Задайте хоча б один з коефіцієнтів a3 або b3 ненульовим."
            );
        }
    }
}