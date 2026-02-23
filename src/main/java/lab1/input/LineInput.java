package lab1.input;

import java.io.PrintStream;

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

    public Line readL1_General() {
        out.println("L1 (подання 1): a1*x + b1*y + c1 = 0");
        out.print("  Введіть a1: ");
        int a1 = RangeValidator.requireInRange(reader.readInt());
        out.print("  Введіть b1: ");
        int b1 = RangeValidator.requireInRange(reader.readInt());
        out.print("  Введіть c1: ");
        int c1 = RangeValidator.requireInRange(reader.readInt());

        LineConstraintsValidator.validateGeneral(a1, b1);
        return LineFactory.fromGeneral(a1, b1, c1);
    }

    public Line readL2_Intercepts() {
        out.println("L2 (подання 4, у відрізках): x/a2 + y/b2 = 1");
        out.print("  Введіть a2 (≠0): ");
        int a2 = RangeValidator.requireInRange(reader.readInt());
        out.print("  Введіть b2 (≠0): ");
        int b2 = RangeValidator.requireInRange(reader.readInt());

        LineConstraintsValidator.validateIntercepts(a2, b2);
        return LineFactory.fromIntercepts(a2, b2);
    }

    public Line readL3_PointNormal() {
        out.println("L3 (подання 6): a3(x-x0) + b3(y-y0) = 0");
        out.print("  Введіть x0: ");
        int x0 = RangeValidator.requireInRange(reader.readInt());
        out.print("  Введіть y0: ");
        int y0 = RangeValidator.requireInRange(reader.readInt());
        out.print("  Введіть a3: ");
        int a3 = RangeValidator.requireInRange(reader.readInt());
        out.print("  Введіть b3: ");
        int b3 = RangeValidator.requireInRange(reader.readInt());

        LineConstraintsValidator.validatePointNormal(a3, b3);
        return LineFactory.fromPointNormal(x0, y0, a3, b3);
    }
}