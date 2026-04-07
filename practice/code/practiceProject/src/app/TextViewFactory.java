package app;

/**
 * Конкретна фабрика для створення об'єктів відображення результатів у текстовому вигляді.
 * Реалізує інтерфейс {@link ViewFactory} (шаблон Factory Method).
 * Забезпечує створення екземпляра класу {@link TextView}.
 *
 * @author Aboimov Vlad
 * @version 1.2
 */
public class TextViewFactory implements ViewFactory {
    /**
     * Переоцінений фабрикуючий метод, який повертає новий об'єкт {@link TextView}.
     * Дозволяє системі абстрагуватися від конкретного класу відображення,
     * використовуючи інтерфейс {@link ResultView}.
     *
     * @return новий об'єкт {@link TextView} для текстового виводу результатів.
     */
    @Override
    public ResultView createView() {
        return new TextView();
    }
}
