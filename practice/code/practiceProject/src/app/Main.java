package app;

import java.util.Scanner;
import java.util.Stack;

/**
 * Оновлений головний клас із підтримкою скасування операцій (Undo),
 * макрокоманд та патерну Singleton.
 *
 * @author Aboimov Vlad
 * @version 1.3
 */
public class Main {
    /** Використання Singleton екземпляра Solver */
    private final Solver solver = Solver.getInstance();

    /** Стек для зберігання виконаних команд (для Undo) */
    private final Stack<Command> undoStack = new Stack<>();

    private ViewFactory factory;
    private ResultView view;
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        System.out.println("Оберіть режим: 1-Текст, 2-Таблиця");
        int mode = scanner.nextInt();

        if (mode == 2) {
            System.out.print("Введіть ширину таблиці (напр. 40): ");
            int width = scanner.nextInt();
            factory = new TableViewFactory(width);
        } else {
            factory = new TextViewFactory();
        }

        view = factory.createView();

        while (true) {
            System.out.println("\nМеню: 1-Обчислити | 2-Скасувати (Undo) | 3-Макрокоманда | 4-Історія | 0-Вихід");
            System.out.print("Вибір: ");
            String choice = scanner.next();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.println("Введіть 4 кути:");
                        double[] angles = new double[4];
                        for (int i = 0; i < 4; i++) angles[i] = scanner.nextDouble();

                        Command cmd = new CalcCommand(solver, angles);
                        cmd.execute();
                        undoStack.push(cmd);

                        view.viewAll(solver.getHistory().get(solver.getHistory().size() - 1));
                    }
                    case "2" -> {
                        if (!undoStack.isEmpty()) {
                            Command cmd = undoStack.pop();
                            cmd.undo();
                            System.out.println("Останню операцію скасовано.");
                        } else {
                            System.out.println("Немає операцій для скасування.");
                        }
                    }
                    case "3" -> {
                        System.out.println("Виконання макрокоманди (автоматичне обчислення 3-х пресетів)...");
                        MacroCommand macro = new MacroCommand();

                        // Додаємо кілька команд у макрокоманду
                        macro.add(new CalcCommand(solver, new double[]{0, 0, 0, 0}));
                        macro.add(new CalcCommand(solver, new double[]{Math.PI/2, Math.PI/2, Math.PI/2, Math.PI/2}));
                        macro.add(new CalcCommand(solver, new double[]{1.0, 2.0, 3.0, 4.0}));

                        macro.execute();
                        undoStack.push(macro);
                        System.out.println("Макрокоманду виконано.");
                    }
                    case "4" -> {
                        if (solver.getHistory().isEmpty()) {
                            System.out.println("Історія порожня.");
                        } else {
                            for (DataModel d : solver.getHistory()) {
                                view.viewBody(d, "Запис історії");
                            }
                        }
                    }
                    case "0" -> System.exit(0);
                    default -> System.out.println("Невірна команда.");
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Main().menu();
    }
}
