package lab1.app;

import java.io.InputStream;
import java.io.PrintStream;

import lab1.exception.LabInputException;
import lab1.geometry.ThreeLinesAnalyzer;
import lab1.input.LineInput;
import lab1.io.ConsoleReader;
import lab1.model.Line;
import lab1.result.AnalysisResult;

public final class ConsoleApp {

    public void runOnce(InputStream in, PrintStream out) {
        ConsoleReader reader = new ConsoleReader(in);
        LineInput input = new LineInput(reader, out);

        try {
            Line l1 = input.readL1_General();
            Line l2 = input.readL2_Intercepts();
            Line l3 = input.readL3_PointNormal();

            AnalysisResult res = ThreeLinesAnalyzer.analyze(l1, l2, l3);

            out.println(res.message);

        } catch (LabInputException ex) {
            out.println("ПОМИЛКА: " + ex.getMessage());
            out.println("ЯК ВИПРАВИТИ: " + ex.getFixHint());
        }
    }
}