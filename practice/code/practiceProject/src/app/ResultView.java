package app;

/**
 * Інтерфейс об'єктів, що представляють набір методів для відображення результатів обчислень.
 * Виступає в ролі "продукту" у шаблоні проектування Factory Method.
 * Визначає структуру виведення даних для різних типів представлень (текстове, графічне тощо).
 *
 * @author Aboimov Vlad
 * @version 1.2
 */
public interface ResultView {
    /** Виведення заголовка звіту */
    void viewHeader();
    /** Виведення основної частини з даними */
    void viewBody(DataModel data);
    /** Виведення завершальної частини */
    void viewFooter();

    default void viewAll(DataModel data) {
        viewHeader();
        viewBody(data);
        viewFooter();
    }
}
