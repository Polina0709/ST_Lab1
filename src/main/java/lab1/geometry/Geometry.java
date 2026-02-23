package lab1.geometry;

import lab1.model.Line;
import lab1.model.Point;
import lab1.util.MathUtil;

public final class Geometry {
    private Geometry() {}

    public static double determinant(Line l1, Line l2) {
        return l1.A * l2.B - l2.A * l1.B;
    }

    public static boolean areCoincident(Line l1, Line l2) {
        return MathUtil.isZero(l1.A * l2.B - l2.A * l1.B)
                && MathUtil.isZero(l1.A * l2.C - l2.A * l1.C)
                && MathUtil.isZero(l1.B * l2.C - l2.B * l1.C);
    }

    public static PairIntersection intersectTwoLines(Line l1, Line l2) {
        double D = determinant(l1, l2);

        if (MathUtil.isZero(D)) {
            if (areCoincident(l1, l2)) {
                return PairIntersection.coincident();
            }
            return PairIntersection.parallelDistinct();
        }

        double x = (l1.B * l2.C - l2.B * l1.C) / D;
        double y = (l1.C * l2.A - l2.C * l1.A) / D;

        return PairIntersection.intersect(new Point(x, y));
    }
}