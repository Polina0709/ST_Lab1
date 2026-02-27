package lab1.input;

import java.io.PrintStream;

import lab1.exception.LabInputException;
import lab1.factory.LineFactory;
import lab1.io.ConsoleReader;
import lab1.model.Line;
import lab1.validation.LineConstraintsValidator;
import lab1.validation.RangeValidator;

public final class LineInput {

    private final ConsoleReader reader;
    private final PrintStream out;

    public LineInput(ConsoleReader reader, PrintStream out) {
        this.reader = reader;
        this.out = out;
    }

    private int readInRangeWithRetry(String prompt) {
        while (true) {
            try {
                out.print(prompt);
                int value = reader.readInt();
                return RangeValidator.requireInRange(value);
            } catch (LabInputException ex) {
                out.println("ПОМИЛКА: " + ex.getMessage());
                out.println("ЯК ВИПРАВИТИ: " + ex.getFixHint());
            }
        }
    }

    private int readNonZeroInRangeWithRetry(String prompt, String fieldName) {
        while (true) {
            int v = readInRangeWithRetry(prompt);
            if (v != 0) return v;

            out.println("ПОМИЛКА: Некоректне значення: " + fieldName + " повинно бути ≠ 0.");
            out.println("ЯК ВИПРАВИТИ: Введіть ненульове значення.");
        }
    }

    public Line readL1_General() {
        out.println("L1 (подання 1): a1*x + b1*y + c1 = 0");

        int a1 = readInRangeWithRetry("  Введіть a1: ");
        int b1 = readInRangeWithRetry("  Введіть b1: ");
        int c1 = readInRangeWithRetry("  Введіть c1: ");

        while (a1 == 0 && b1 == 0) {
            out.println("ПОМИЛКА: Некоректна пряма L1: a1=0 і b1=0 (заборонено).");
            out.println("ЯК ВИПРАВИТИ: Введіть ненульове значення для a1 або b1.");
            b1 = readInRangeWithRetry("  Введіть b1 знову: ");
        }

        LineConstraintsValidator.validateGeneral(a1, b1);

        return LineFactory.fromGeneral(a1, b1, c1);
    }

    public Line readL2_Intercepts() {
        out.println("L2 (подання 4, у відрізках): x/a2 + y/b2 = 1");

        int a2 = readNonZeroInRangeWithRetry("  Введіть a2 (≠0): ", "a2");
        int b2 = readNonZeroInRangeWithRetry("  Введіть b2 (≠0): ", "b2");

        LineConstraintsValidator.validateIntercepts(a2, b2);

        return LineFactory.fromIntercepts(a2, b2);
    }

    public Line readL3_PointNormal() {
        out.println("L3 (подання 6): a3(x-x0) + b3(y-y0) = 0");

        int x0 = readInRangeWithRetry("  Введіть x0: ");
        int y0 = readInRangeWithRetry("  Введіть y0: ");

        int a3 = readInRangeWithRetry("  Введіть a3: ");
        int b3 = readInRangeWithRetry("  Введіть b3: ");

        while (a3 == 0 && b3 == 0) {
            out.println("ПОМИЛКА: Некоректна пряма L3: a3=0 і b3=0 (заборонено).");
            out.println("ЯК ВИПРАВИТИ: Введіть ненульове значення для a3 або b3.");
            b3 = readInRangeWithRetry("  Введіть b3 знову: ");
        }

        LineConstraintsValidator.validatePointNormal(a3, b3);

        return LineFactory.fromPointNormal(x0, y0, a3, b3);
    }
}