package lab1.geometry;

import lab1.model.Point;

public final class PairIntersection {
    public enum Kind { INTERSECT_AT_POINT, PARALLEL_DISTINCT, COINCIDENT }

    public final Kind kind;
    public final Point point;

    private PairIntersection(Kind kind, Point point) {
        this.kind = kind;
        this.point = point;
    }

    public static PairIntersection intersect(Point p) {
        return new PairIntersection(Kind.INTERSECT_AT_POINT, p);
    }

    public static PairIntersection parallelDistinct() {
        return new PairIntersection(Kind.PARALLEL_DISTINCT, null);
    }

    public static PairIntersection coincident() {
        return new PairIntersection(Kind.COINCIDENT, null);
    }
}