package lab1;

import lab1.factory.LineFactory;
import lab1.geometry.ThreeLinesAnalyzer;
import lab1.model.Line;
import lab1.result.AnalysisResult;
import lab1.result.CaseType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameworkStrategyValidTest {

    record In(int a1,int b1,int c1, int a2,int b2, int x0,int y0,int a3,int b3, CaseType expected) {}

    private static AnalysisResult run(In in) {
        Line l1 = LineFactory.fromGeneral(in.a1, in.b1, in.c1);
        Line l2 = LineFactory.fromIntercepts(in.a2, in.b2);
        Line l3 = LineFactory.fromPointNormal(in.x0, in.y0, in.a3, in.b3);
        return ThreeLinesAnalyzer.analyze(l1, l2, l3);
    }

    static Stream<In> allCoincident6() {
        int MIN = -119, MAX = 119;

        return Stream.of(
                coincidentFromL2(MIN, MIN+1, /*point*/ 0, 0),
                coincidentFromL2(MAX, MAX-1, 0, 0),
                coincidentFromL2(2, 3, 2, 0),
                coincidentFromL2(MIN+1, 2, 1, 1),
                coincidentFromL2(MIN+2, MIN+3, 1, 0),
                coincidentFromL2(MAX-2, MAX-3, 1, 0)
        );
    }

    private static In coincidentFromL2(int a2, int b2, int px, int py) {
        int A = b2;
        int B = a2;
        int C = -a2*b2;

        int y = (int)((-C - (long)A*px) / (long)B);
        int x0 = 0;
        int y0 = b2;

        return new In(
                A, B, C,
                a2, b2,
                x0, y0, A, B,
                CaseType.ALL_COINCIDENT
        );
    }

    static Stream<In> noIntersections6() {
        int MIN = -119, MAX = 119;

        return Stream.of(
                parallelTriple(MIN, MIN+1, /*cShift*/ 1),
                parallelTriple(MAX, MAX-1, 1),
                parallelTriple(2, 3, 1),
                parallelTriple(MIN+1, 2, 2),
                parallelTriple(MIN+2, MIN+3, 3),
                parallelTriple(MAX-2, MAX-3, 3)
        );
    }

    private static In parallelTriple(int a2, int b2, int cShift) {
        int A = b2, B = a2, C2 = -a2*b2;

        int C1 = C2 - cShift;

        int C3 = C2 + cShift;
        int x0 = 0;
        int y0 = (int)(-C3 / (long)B);

        return new In(
                A, B, C1,
                a2, b2,
                x0, y0, A, B,
                CaseType.NO_INTERSECTIONS
        );
    }

    static Stream<In> onePoint6() {
        int MIN = -119, MAX = 119;

        return Stream.of(
                onePointCoincidentPair(1, 2, 2),
                onePointCoincidentPair(MIN+1, 2, 2),
                onePointCoincidentPair(MAX-1, 2, 2),
                onePointCoincidentPair(1, MIN+1, MAX-1),
                onePointCoincidentPair(1, MIN+2, MIN+3),
                onePointCoincidentPair(1, MAX-2, MAX-3)
        );
    }

    private static In onePointCoincidentPair(int xConst, int a2, int b2) {

        int a1 = 1, b1 = 0, c1 = -xConst;

        int x0 = xConst, y0 = 0, a3 = 1, b3 = 0;

        return new In(a1,b1,c1, a2,b2, x0,y0,a3,b3, CaseType.ONE_POINT);
    }

    static Stream<In> twoPoints6() {
        int MIN = -119, MAX = 119;

        return Stream.of(
                twoPointsVariant(0, 2, 2, 2),
                twoPointsVariant(0, 2, MIN+1, 2),
                twoPointsVariant(0, 2, MAX-1, 2),
                twoPointsVariant(0, 2, 2, MIN+1),
                twoPointsVariant(0, 2, MIN+2, MIN+3),
                twoPointsVariant(0, 2, MAX-2, MAX-3)
        );
    }

    private static In twoPointsVariant(int x1, int x2, int a2, int b2) {

        int a1=1,b1=0,c1=-x1;

        int x0=x2, y0=0, a3=1, b3=0;

        return new In(a1,b1,c1, a2,b2, x0,y0,a3,b3, CaseType.TWO_POINTS);
    }

    static Stream<In> threePoints6() {
        int MIN = -119, MAX = 119;

        return Stream.of(
                threePointsVariant(2, 2),
                threePointsVariant(MIN+1, 2),
                threePointsVariant(MAX-1, 2),
                threePointsVariant(2, MIN+1),
                threePointsVariant(MIN+2, MIN+3),
                threePointsVariant(MAX-2, MAX-3)
        );
    }

    private static In threePointsVariant(int a2, int b2) {
        int a1=1,b1=0,c1=0;
        int x0=0,y0=0,a3=0,b3=1;
        return new In(a1,b1,c1, a2,b2, x0,y0,a3,b3, CaseType.THREE_POINTS);
    }

    @ParameterizedTest
    @MethodSource("allCoincident6")
    void valid_allCoincident(In in) { assertEquals(in.expected, run(in).caseType); }

    @ParameterizedTest
    @MethodSource("noIntersections6")
    void valid_noIntersections(In in) { assertEquals(in.expected, run(in).caseType); }

    @ParameterizedTest
    @MethodSource("onePoint6")
    void valid_onePoint(In in) { assertEquals(in.expected, run(in).caseType); }

    @ParameterizedTest
    @MethodSource("twoPoints6")
    void valid_twoPoints(In in) { assertEquals(in.expected, run(in).caseType); }

    @ParameterizedTest
    @MethodSource("threePoints6")
    void valid_threePoints(In in) { assertEquals(in.expected, run(in).caseType); }
}