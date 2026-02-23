package lab1.factory;

import lab1.model.Line;

public final class LineFactory {
    private LineFactory() {}

    // Подання 1: (a1, b1, c1) => a1*x + b1*y + c1 = 0
    public static Line fromGeneral(int a1, int b1, int c1) {
        return new Line(a1, b1, c1);
    }

    // Подання 4: у відрізках (a2, b2): x/a2 + y/b2 = 1
    // => b2*x + a2*y - a2*b2 = 0
    public static Line fromIntercepts(int a2, int b2) {
        double A = b2;
        double B = a2;
        double C = -1.0 * a2 * b2;
        return new Line(A, B, C);
    }

    // Подання 6: (x0, y0, a3, b3): a3(x-x0)+b3(y-y0)=0
    // => a3*x + b3*y - (a3*x0 + b3*y0) = 0
    public static Line fromPointNormal(int x0, int y0, int a3, int b3) {
        double A = a3;
        double B = b3;
        double C = -1.0 * (a3 * (double)x0 + b3 * (double)y0);
        return new Line(A, B, C);
    }
}
