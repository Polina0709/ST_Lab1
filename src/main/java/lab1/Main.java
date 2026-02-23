package lab1;

import lab1.app.ConsoleApp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        Scanner sc = new Scanner(System.in);

        while (true) {
            app.runOnce(System.in, System.out);

            System.out.print("Ще один запуск? (1 - так, 0 - ні): ");
            String s = sc.nextLine().trim();
            if (!s.equals("1")) break;
        }

        System.out.println("Завершено.");
    }
}