package lab1;

import lab1.app.ConsoleApp;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleAppIntegrationTest {

    private String runWithInput(String input) {
        ConsoleApp app = new ConsoleApp();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_8);
        app.runOnce(in, out);
        return baos.toString(StandardCharsets.UTF_8);
    }

    @Test
    void integration_case_allCoincident_messageExact() {
        // L1: 3 2 -6
        // L2: 2 3
        // L3: 2 0 3 2
        String input = String.join("\n",
                "3","2","-6",
                "2","3",
                "2","0","3","2"
        ) + "\n";

        String output = runWithInput(input);
        assertTrue(output.contains("Прямі співпадають"));
    }

    @Test
    void integration_case_noIntersections_messageExact() {
        String input = String.join("\n",
                "3","2","-7",
                "2","3",
                "0","0","3","2"
        ) + "\n";

        String output = runWithInput(input);
        assertTrue(output.contains("Прямі не перетинаються"));
    }

    @Test
    void integration_case_onePoint_messagePrefix() {
        String input = String.join("\n",
                "1","-1","0",
                "2","2",
                "1","1","1","0"
        ) + "\n";

        String output = runWithInput(input);
        assertTrue(output.contains("Єдина точка перетину прямих (x0, y0), x0="));
    }

    @Test
    void integration_case_twoPoints_messagePrefix() {
        String input = String.join("\n",
                "1","0","0",
                "2","2",
                "2","0","1","0"
        ) + "\n";

        String output = runWithInput(input);
        assertTrue(output.contains("Дві точки перетину прямих (x1, y1) ="));
    }

    @Test
    void integration_case_threePoints_messagePrefix() {
        String input = String.join("\n",
                "1","0","0",
                "2","2",
                "0","0","0","1"
        ) + "\n";

        String output = runWithInput(input);
        assertTrue(output.contains("Три точки перетину прямих (x1, y1)"));
    }
}