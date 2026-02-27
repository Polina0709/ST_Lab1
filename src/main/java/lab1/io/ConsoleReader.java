package lab1.io;

import java.util.Scanner;
import lab1.exception.InvalidLineException;

public final class ConsoleReader {

    private final Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt() {
        String s;

        try {
            s = scanner.nextLine().trim();
        } catch (Exception ex) {
            throw new InvalidLineException(
                    "Не вистачає введених даних.",
                    "Переконайтесь, що ви ввели всі необхідні значення."
            );
        }

        if (!s.matches("[+-]?\\d+")) {
            throw new InvalidLineException(
                    "Некоректний формат: '" + s + "' не є цілим числом.",
                    "Введіть ціле число (наприклад -5, 0, 17)."
            );
        }

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            throw new InvalidLineException(
                    "Число '" + s + "' виходить за межі типу int.",
                    "Введіть число в допустимому діапазоні."
            );
        }
    }
}