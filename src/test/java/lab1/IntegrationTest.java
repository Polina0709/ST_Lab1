package lab1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest {

    private String runWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_8);

        Main.runOnce(in, out);

        // Нормалізація для різних ОС
        return baos.toString(StandardCharsets.UTF_8).replace("\r", "");
    }

    @Test
    void integration_case_allCoincident_messageExact() {
        String input = String.join("\n",
                "3","2","-6",
                "2","3",
                "2","0","3","2"
        ) + "\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Прямі співпадають"),
                "Output was:\n" + output);
    }

    @Test
    void integration_case_noIntersections_messageExact() {
        String input = String.join("\n",
                "3","2","-7",
                "2","3",
                "0","0","3","2"
        ) + "\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Прямі не перетинаються"),
                "Output was:\n" + output);
    }

    @Test
    void integration_case_onePoint_messagePrefix() {
        String input = String.join("\n",
                "1","-1","0",
                "2","2",
                "1","1","1","0"
        ) + "\n";

        String output = runWithInput(input);

        // Стабільно: не залежить від пробілів/формату чисел (x0= / x0= )
        assertTrue(output.contains("Єдина точка перетину прямих"),
                "Output was:\n" + output);
    }

    @Test
    void integration_case_twoPoints_messagePrefix() {
        String input = String.join("\n",
                "1","0","0",
                "2","2",
                "2","0","1","0"
        ) + "\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Дві точки перетину прямих"),
                "Output was:\n" + output);
    }

    @Test
    void integration_case_threePoints_messagePrefix() {
        String input = String.join("\n",
                "1","0","0",
                "2","2",
                "0","0","0","1"
        ) + "\n";

        String output = runWithInput(input);

        assertTrue(output.contains("Три точки перетину прямих"),
                "Output was:\n" + output);
    }
}