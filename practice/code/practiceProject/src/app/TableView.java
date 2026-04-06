package app;

import java.util.Arrays;

/**
 * Клас для представлення результатів у вигляді текстової таблиці.
 * Демонструє Overriding (перевизначення).
 */
public class TableView implements ResultView {
    private int width;

    public TableView(int width) {
        this.width = width;
    }

    @Override
    public void viewHeader() {
        System.out.println("+".strip() + "-".repeat(width) + "+");
        System.out.printf("| %-" + (width-2) + "s |\n", "ТАБЛИЧНИЙ ЗВІТ");
        System.out.println("+".strip() + "-".repeat(width) + "+");
    }

    @Override
    public void viewBody(DataModel data) {
        String args = Arrays.toString(data.getAngles());
        System.out.printf("| Args: %-" + (width-10) + "s |\n", args);
        System.out.printf("| Res:  %-" + (width-10) + "d |\n", data.getResult());
    }

    @Override
    public void viewFooter() {
        System.out.println("+".strip() + "-".repeat(width) + "+");
    }
}
