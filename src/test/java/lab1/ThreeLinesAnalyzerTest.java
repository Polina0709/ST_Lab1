package lab1;

import lab1.factory.LineFactory;
import lab1.geometry.ThreeLinesAnalyzer;
import lab1.model.Line;
import lab1.model.Point;
import lab1.result.AnalysisResult;
import lab1.result.CaseType;
import lab1.util.MathUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ThreeLinesAnalyzerTest {

    private static void assertSamePointSet(List<Point> actual, List<Point> expected) {
        if (actual == null) actual = List.of();
        if (expected == null) expected = List.of();

        assertEquals(expected.size(), actual.size(), "Різна кількість точок");

        List<Point> unused = new ArrayList<>(actual);
        for (Point ep : expected) {
            int idx = findAndRemove(unused, ep);
            assertTrue(idx >= 0, "Не знайдено очікувану точку " + ep + " серед фактичних " + actual);
        }
    }

    private static int findAndRemove(List<Point> list, Point target) {
        for (int i = 0; i < list.size(); i++) {
            if (MathUtil.samePoint(list.get(i), target)) {
                list.remove(i);
                return i;
            }
        }
        return -1;
    }

    @Test
    void case1_allCoincident() {
        // Case 1: всі співпадають (3x + 2y - 6 = 0)
        Line l1 = LineFactory.fromGeneral(3, 2, -6);
        Line l2 = LineFactory.fromIntercepts(2, 3);         // -> 3x+2y-6=0
        Line l3 = LineFactory.fromPointNormal(2, 0, 3, 2);  // -> 3x+2y-6=0

        AnalysisResult res = ThreeLinesAnalyzer.analyze(l1, l2, l3);

        assertEquals(CaseType.ALL_COINCIDENT, res.caseType);
        assertTrue(res.points.isEmpty());
    }

    @Test
    void case2_noIntersections_allParallelDistinct() {
        // Case 2: 0 точок (усі паралельні й різні)
        Line l1 = LineFactory.fromGeneral(3, 2, -7);   // 3x+2y-7=0
        Line l2 = LineFactory.fromIntercepts(2, 3);    // 3x+2y-6=0
        Line l3 = LineFactory.fromPointNormal(0, 0, 3, 2); // 3x+2y=0

        AnalysisResult res = ThreeLinesAnalyzer.analyze(l1, l2, l3);

        assertEquals(CaseType.NO_INTERSECTIONS, res.caseType);
        assertTrue(res.points.isEmpty());
    }

    @Test
    void case3_onePoint_commonIntersection() {
        // Case 3: 1 точка (1,1)
        Line l1 = LineFactory.fromGeneral(1, -1, 0);        // x-y=0
        Line l2 = LineFactory.fromIntercepts(2, 2);         // x+y-2=0
        Line l3 = LineFactory.fromPointNormal(1, 1, 1, 0);  // x=1

        AnalysisResult res = ThreeLinesAnalyzer.analyze(l1, l2, l3);

        assertEquals(CaseType.ONE_POINT, res.caseType);
        assertSamePointSet(res.points, List.of(new Point(1, 1)));
    }

    @Test
    void case4_twoPoints_onePairParallel() {
        // Case 4: 2 точки: (0,2) і (2,0)
        Line l1 = LineFactory.fromGeneral(1, 0, 0);        // x=0
        Line l2 = LineFactory.fromIntercepts(2, 2);        // x+y-2=0
        Line l3 = LineFactory.fromPointNormal(2, 0, 1, 0); // x=2

        AnalysisResult res = ThreeLinesAnalyzer.analyze(l1, l2, l3);

        assertEquals(CaseType.TWO_POINTS, res.caseType);
        assertSamePointSet(res.points, List.of(new Point(0, 2), new Point(2, 0)));
    }

    @Test
    void case5_threePoints_allDifferent() {
        // Case 5: 3 точки: (0,0), (0,2), (2,0)
        Line l1 = LineFactory.fromGeneral(1, 0, 0);        // x=0
        Line l2 = LineFactory.fromIntercepts(2, 2);        // x+y-2=0
        Line l3 = LineFactory.fromPointNormal(0, 0, 0, 1); // y=0

        AnalysisResult res = ThreeLinesAnalyzer.analyze(l1, l2, l3);

        assertEquals(CaseType.THREE_POINTS, res.caseType);
        assertSamePointSet(res.points, List.of(new Point(0, 0), new Point(0, 2), new Point(2, 0)));
    }
}