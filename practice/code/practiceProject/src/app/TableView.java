package app;

import java.util.Arrays;

/**
 * Клас для представлення результатів обчислень у вигляді текстової таблиці.
 * Цей клас демонструє механізм <b>Overriding</b> (перевизначення методів),
 * адаптуючи стандартний вивід інтерфейсу {@link ResultView} під табличний формат.
 *
 * @author Aboimov Vlad
 * @version 1.0
 */
public class TableView implements ResultView {
    private int width;

    /**
     * Конструктор класу TableView.
     *
     * @param width ширина таблиці, яка буде використовуватися для форматування виводу.
     */
    public TableView(int width) {
        this.width = width;
    }

    /**
     * Виводить верхню межу таблиці та її заголовок.
     * Реалізує перевизначення методу інтерфейсу для малювання рамки.
     */
    @Override
    public void viewHeader() {
        System.out.println("+".strip() + "-".repeat(width) + "+");
        System.out.printf("| %-" + (width-2) + "s |\n", "ТАБЛИЧНИЙ ЗВІТ");
        System.out.println("+".strip() + "-".repeat(width) + "+");
    }

    /**
     * Виводить основні дані результатів обчислення у табличному форматі.
     * Форматує вхідні аргументи та фінальний результат згідно з шириною таблиці.
     *
     * @param data об'єкт {@link DataModel}, дані якого необхідно відобразити.
     */
    @Override
    public void viewBody(DataModel data) {
        String args = Arrays.toString(data.getAngles());
        System.out.printf("| Args: %-" + (width-10) + "s |\n", args);
        System.out.printf("| Res:  %-" + (width-10) + "d |\n", data.getResult());
    }

    /**
     * Виводить нижню межу таблиці, завершуючи її формування.
     */
    @Override
    public void viewFooter() {
        System.out.println("+".strip() + "-".repeat(width) + "+");
    }
}
