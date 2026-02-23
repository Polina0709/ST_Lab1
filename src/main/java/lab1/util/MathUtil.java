package lab1.util;

import lab1.model.Point;

public final class MathUtil {
    private MathUtil() {}

    public static final double EPS = 1e-8;

    public static boolean isZero(double z) {
        return Math.abs(z) <= EPS;
    }

    public static boolean equals(double a, double b) {
        return isZero(a - b);
    }

    public static boolean samePoint(Point p, Point q) {
        return equals(p.x, q.x) && equals(p.y, q.y);
    }
}