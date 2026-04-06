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
    private ViewFactory factory;
    private ResultView view;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Основний метод для запуску діалогового меню.
     * Забезпечує обробку команд: обчислення, перегляд історії, збереження та завантаження.
     */
    public void menu() {
        // 1. Користувач обирає режим відображення
        System.out.println("Оберіть режим: 1-Текст, 2-Таблиця");
        int mode = scanner.nextInt();

        if (mode == 2) {
            System.out.print("Введіть ширину таблиці (напр. 40): ");
            int width = scanner.nextInt();
            factory = new TableViewFactory(width);
        } else {
            factory = new TextViewFactory();
        }

        // Динамічне призначення об'єкта (Dynamic Dispatch)
        view = factory.createView();

        while (true) {
            System.out.println("1. Обчислити | 2. Історія | 0. Вихід");
            String choice = scanner.next();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.println("Введіть 4 кути:");
                        double[] angles = new double[4];
                        for (int i = 0; i < 4; i++) angles[i] = scanner.nextDouble();
                        DataModel res = solver.calculate(angles);

                        // Поліморфний виклик методу
                        view.viewAll(res);
                    }
                    case "2" -> {
                        // Демонстрація Overloading (перевантаження)
                        for (DataModel d : solver.getHistory()) {
                            view.viewBody(d, "Запис історії");
                        }
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
