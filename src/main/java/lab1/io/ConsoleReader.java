package lab1.io;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import lab1.exception.InvalidLineException;

public final class ConsoleReader {
    private final Scanner scanner;

    public ConsoleReader(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public int readInt() {
        final String s;
        try {
            s = scanner.nextLine().trim();
        } catch (NoSuchElementException ex) {
            throw new InvalidLineException(
                    "Не вистачає введених даних (кінець вводу).",
                    "Переконайтесь, що ви ввели всі потрібні числа (для L1: 3, L2: 2, L3: 4)."
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
                    "Число '" + s + "' не вдалося прочитати як int (переповнення).",
                    "Введіть число в межах типу int та в дозволеному діапазоні."
            );
        }
    }
}