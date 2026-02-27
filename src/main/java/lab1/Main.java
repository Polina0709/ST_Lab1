package lab1;

import lab1.geometry.ThreeLinesAnalyzer;
import lab1.input.LineInput;
import lab1.io.ConsoleReader;
import lab1.model.Line;
import lab1.result.AnalysisResult;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void runOnce(InputStream in, PrintStream out) {

        Scanner sc = new Scanner(in);
        ConsoleReader reader = new ConsoleReader(sc);
        LineInput input = new LineInput(reader, out);

        Line l1 = input.readL1_General();
        Line l2 = input.readL2_Intercepts();
        Line l3 = input.readL3_PointNormal();

        AnalysisResult res = ThreeLinesAnalyzer.analyze(l1, l2, l3);
        out.println(res.message);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            runOnce(System.in, System.out);

            System.out.print("Ще один запуск? (1 - так, 0 - ні): ");
            String s = sc.nextLine().trim();

            if (!s.equals("1")) {
                break;
            }
        }

        System.out.println("Завершено.");
    }
}