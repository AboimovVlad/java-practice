package app;

import java.util.Arrays;

/**
 * Конкретна реалізація інтерфейсу {@link ResultView} для текстового виведення результатів.
 * Забезпечує форматування та виведення даних об'єкта {@link DataModel} у консоль.
 * Виступає як "Конкретний продукт" у шаблоні проектування Factory Method.
 *
 * @author Aboimov Vlad
 * @version 1.2
 */
public class TextView implements ResultView{
    /**
     * Виводить у консоль декоративний заголовок текстового звіту.
     */
    @Override
    public void viewHeader() {
        System.out.println("++++++++++++++++ Звіт Обчислень ++++++++++++++++");
    }

    /**
     * Виводить основні дані результатів обчислення: вхідні аргументи,
     * фінальний результат та час створення об'єкта (якщо він доступний).
     *
     * @param data об'єкт {@link DataModel}, дані якого необхідно вивести.
     */
    @Override
    public void viewBody(DataModel data) {
        System.out.println("Аргументи: " + Arrays.toString(data.getAngles()));
        System.out.println("Результат: " + data.getResult());
        System.out.println("Час: " + data.getCalcTime());
    }

    /**
     * Виводить у консоль завершальну лінію звіту та додає порожній рядок для візуального розділення.
     */
    @Override
    public void viewFooter() {
        System.out.println("==============================");
    }
}
