package lab1.result;

import java.util.List;
import lab1.model.Point;

public final class MessageFormatter {
    private MessageFormatter() {}

    private static String fmt(double v) {
        return String.format("%.10f", v);
    }

    public static String coincident() {
        return "Прямі співпадають";
    }

    public static String noIntersections() {
        return "Прямі не перетинаються";
    }

    public static String onePoint(Point p) {
        return "Єдина точка перетину прямих (x0, y0), x0= " + fmt(p.x) + ", y0= " + fmt(p.y);
    }

    public static String twoPoints(Point p1, Point p2) {
        return "Дві точки перетину прямих (x1, y1) = (" + fmt(p1.x) + ", " + fmt(p1.y) + "), (x2, y2) = ("
                + fmt(p2.x) + ", " + fmt(p2.y) + ")";
    }

    public static String threePoints(Point p1, Point p2, Point p3) {
        return "Три точки перетину прямих (x1, y1) , (x2, y2), (x3, y3), "
                + "x1= " + fmt(p1.x) + ", y1= " + fmt(p1.y) + ", "
                + "x2= " + fmt(p2.x) + ", y2= " + fmt(p2.y) + ", "
                + "x3= " + fmt(p3.x) + ", y3= " + fmt(p3.y) + ", i=1,2,3";
    }

    public static String from(CaseType type, List<Point> points) {
        return switch (type) {
            case ALL_COINCIDENT -> coincident();
            case NO_INTERSECTIONS -> noIntersections();
            case ONE_POINT -> onePoint(points.get(0));
            case TWO_POINTS -> twoPoints(points.get(0), points.get(1));
            case THREE_POINTS -> threePoints(points.get(0), points.get(1), points.get(2));
        };
    }
}