package app;

import java.io.*;
import java.util.Scanner;

/**
 * Головний клас програми для демонстрації збереження та відновлення стану об'єкта.
 * Реалізує діалоговий режим роботи з користувачем через консоль.
 * Демонструє роботу з серіалізацією та використання transient полів.
 *
 * @author Абоімов Владислав
 * @version 1.0
 */
public class Main {
    /**
     * Точка входу в програму. Забезпечує роботу консольного меню.
     * Дозволяє вводити аргументи, виконувати обчислення, зберігати результати
     * у файл та відновлювати їх.
     *
     * @param args аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Solver solver = new Solver();
        DataModel model = null;

        while (true) {
            System.out.println("\n1. Розрахувати 2. Зберегти 3. Завантажити 4. Вихід");
            int choice = sc.nextInt();

            if (choice == 1) {
                double[] angles = new double[4];
                System.out.println("Введіть 4 числа (через пробіл):");
                for (int i = 0; i < 4; i++) angles[i] = sc.nextDouble();
                model = solver.calculate(angles);
                System.out.println("Готово: " + model);
            } else if (choice == 2 && model != null) {
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
                    out.writeObject(model);
                    System.out.println("Об'єкт збережено.");
                } catch (IOException e) { e.printStackTrace(); }
            } else if (choice == 3) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"))) {
                    model = (DataModel) in.readObject();
                    System.out.println("Об'єкт завантажено: " + model);
                    System.out.println("(Зауважте, час розрахунку тепер null, так як поле transient)");
                } catch (Exception e) { System.out.println("Файл не найден."); }
            } else if (choice == 4) break;
        }
    }
}
