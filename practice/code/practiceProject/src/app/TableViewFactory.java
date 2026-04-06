package app;

/**
 * Конкретна фабрика для створення об'єктів табличного відображення результатів.
 * Реалізує інтерфейс {@link ViewFactory}, впроваджуючи шаблон <b>Factory Method</b>.
 * Дозволяє створювати екземпляри {@link TableView} із заданою користувачем шириною.
 *
 * @author Aboimov Vlad
 * @version 1.0
 */
public class TableViewFactory implements ViewFactory {
    private int tableWidth;

    /**
     * Конструктор фабрики, що приймає параметри конфігурації відображення.
     *
     * @param width ширина таблиці у символах, яку вказав користувач.
     */
    public TableViewFactory(int width) {
        this.tableWidth = width;
    }

    /**
     * Фабрикуючий метод, що створює та повертає новий об'єкт {@link TableView}.
     * Демонструє поліморфізм, повертаючи конкретну реалізацію через інтерфейс {@link ResultView}.
     *
     * @return новий об'єкт табличного відображення з налаштованою шириною.
     */
    @Override
    public ResultView createView() {
        return new TableView(tableWidth);
    }
}
