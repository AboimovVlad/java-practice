package app;

import java.util.Scanner;

/**
 * Головний клас додатка, що керує діалоговим інтерфейсом користувача.
 * Використовує патерн Factory Method для створення об'єктів відображення
 * та делегує обчислювальну логіку класу {@link Solver}.
 *
 * @author Aboimov Vlad
 * @version 1.2
 */
public class Main {
    private final Solver solver = new Solver();
    private final ViewFactory factory = new TextViewFactory();
    private final ResultView view = factory.createView();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Основний метод для запуску діалогового меню.
     * Забезпечує обробку команд: обчислення, перегляд історії, збереження та завантаження.
     */
    public void menu() {
        while (true) {
            System.out.println("1. Обчислити | 2. Історія | 3. Зберегти | 4. Відновити | 0. Вихід");
            System.out.print("Вибір: ");
            String choice = scanner.next();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.println("Введіть 4 кути:");
                        double[] angles = new double[4];
                        for (int i = 0; i < 4; i++) angles[i] = scanner.nextDouble();

                        // Main просто викликає метод Solver
                        DataModel res = solver.calculate(angles);
                        view.viewAll(res); // Використовуємо метод за замовчуванням
                    }
                    case "2" -> {
                        for (DataModel d : solver.getHistory()) view.viewBody(d);
                    }
                    case "3" -> {
                        solver.save("history.ser");
                        System.out.println("Збережено.");
                    }
                    case "4" -> {
                        solver.load("history.ser");
                        System.out.println("Відновлено.");
                    }
                    case "0" -> System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    /**
     * Точка входу в програму.
     * Створює екземпляр класу Main та запускає діалоговий інтерфейс.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        new Main().menu();
    }
}
