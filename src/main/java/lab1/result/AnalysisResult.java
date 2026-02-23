package lab1.result;

import java.util.List;
import lab1.model.Point;

public final class AnalysisResult {
    public final CaseType caseType;
    public final List<Point> points;
    public final String message;

    public AnalysisResult(CaseType caseType, List<Point> points, String message) {
        this.caseType = caseType;
        this.points = points;
        this.message = message;
    }

    public String pointsPretty() {
        if (points == null || points.isEmpty()) return "(немає)";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < points.size(); i++) {
            sb.append("  ").append(i + 1).append(") ").append(points.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}