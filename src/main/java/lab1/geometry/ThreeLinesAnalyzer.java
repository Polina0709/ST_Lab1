package lab1.geometry;

import java.util.ArrayList;
import java.util.List;

import lab1.model.Line;
import lab1.model.Point;
import lab1.result.AnalysisResult;
import lab1.result.CaseType;
import lab1.result.MessageFormatter;
import lab1.util.MathUtil;

public final class ThreeLinesAnalyzer {
    private ThreeLinesAnalyzer() {}

    public static AnalysisResult analyze(Line l1, Line l2, Line l3) {

        if (Geometry.areCoincident(l1, l2)) {
            if (Geometry.areCoincident(l1, l3)) {
                return result(CaseType.ALL_COINCIDENT, List.of());
            }
            PairIntersection i = Geometry.intersectTwoLines(l1, l3);
            if (i.kind == PairIntersection.Kind.PARALLEL_DISTINCT) {
                return result(CaseType.NO_INTERSECTIONS, List.of());
            }
            return result(CaseType.ONE_POINT, List.of(i.point));
        }

        if (Geometry.areCoincident(l1, l3)) {
            PairIntersection i = Geometry.intersectTwoLines(l1, l2);
            if (i.kind == PairIntersection.Kind.PARALLEL_DISTINCT) {
                return result(CaseType.NO_INTERSECTIONS, List.of());
            }
            return result(CaseType.ONE_POINT, List.of(i.point));
        }

        if (Geometry.areCoincident(l2, l3)) {
            PairIntersection i = Geometry.intersectTwoLines(l2, l1);
            if (i.kind == PairIntersection.Kind.PARALLEL_DISTINCT) {
                return result(CaseType.NO_INTERSECTIONS, List.of());
            }
            return result(CaseType.ONE_POINT, List.of(i.point));
        }

        PairIntersection i12 = Geometry.intersectTwoLines(l1, l2);
        PairIntersection i13 = Geometry.intersectTwoLines(l1, l3);

        if (i12.kind == PairIntersection.Kind.INTERSECT_AT_POINT
                && i13.kind == PairIntersection.Kind.INTERSECT_AT_POINT
                && MathUtil.samePoint(i12.point, i13.point)) {
            return result(CaseType.ONE_POINT, List.of(i12.point));
        }

        if (i12.kind == PairIntersection.Kind.PARALLEL_DISTINCT
                && i13.kind == PairIntersection.Kind.PARALLEL_DISTINCT) {
            return result(CaseType.NO_INTERSECTIONS, List.of());
        }

        PairIntersection i23 = Geometry.intersectTwoLines(l2, l3);

        List<Point> unique = new ArrayList<>();
        addUniqueIfPoint(unique, i12);
        addUniqueIfPoint(unique, i13);
        addUniqueIfPoint(unique, i23);

        if (unique.isEmpty()) {
            return result(CaseType.NO_INTERSECTIONS, unique);
        }
        if (unique.size() == 1) {
            return result(CaseType.ONE_POINT, unique);
        }
        if (unique.size() == 2) {
            return result(CaseType.TWO_POINTS, unique);
        }
        return result(CaseType.THREE_POINTS, unique);
    }

    private static AnalysisResult result(CaseType type, List<Point> points) {
        String msg = MessageFormatter.from(type, points);
        return new AnalysisResult(type, points, msg);
    }

    private static void addUniqueIfPoint(List<Point> unique, PairIntersection inter) {
        if (inter.kind != PairIntersection.Kind.INTERSECT_AT_POINT) return;
        Point p = inter.point;
        for (Point q : unique) {
            if (MathUtil.samePoint(p, q)) return;
        }
        unique.add(p);
    }
}