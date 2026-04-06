package app.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import app.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Клас для тестування основної функціональності проекту.
 * Перевіряє коректність обчислень, серіалізацію та роботу з колекціями.
 * @author Aboimov Vlad
 * @version 1.2
 */
public class MainTest {
    /**
     * Тест розрахунку: для кутів, де sin=0, результат повинен бути 0.
     */
    @Test
    public void testCalculation() {
        Solver solver = new Solver();
        double[] angles = {0, Math.PI, 2 * Math.PI, 3 * Math.PI};
        DataModel res = solver.calculate(angles);
        assertEquals(0, res.getResult());
    }

    /**
     * Тест серіалізації: перевірка збереження даних та роботи transient поля.
     */
    @Test
    public void testSerialization() throws Exception {
        DataModel original = new DataModel(new double[]{1, 2, 3, 4}, 5);

        // Запись
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);

        // Чтение
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        DataModel restored = (DataModel) ois.readObject();

        assertEquals(original.getResult(), restored.getResult());
        assertNull(restored.getCalcTime());
    }

    /**
     * Тестування роботи з колекцією результатів.
     * Перевіряє коректність додавання об'єктів та збереження їх властивостей.
     */
    @Test
    public void testCollectionLogic() {
        List<DataModel> list = new ArrayList<>();
        DataModel d1 = new DataModel(new double[]{1, 2}, 5);
        list.add(d1);

        assertEquals(1, list.size());
        assertArrayEquals(new double[]{1, 2}, list.get(0).getAngles(), 0.01);
    }

    /**
     * Тестування поліморфізму (Dynamic Method Dispatch) та Factory Method.
     * Перевіряє, чи створюють фабрики відповідні об'єкти інтерфейсу ResultView.
     */
    @Test
    public void testPolymorphismAndFactories() {
        // Тестування текстової фабрики
        ViewFactory textFactory = new TextViewFactory();
        ResultView textView = textFactory.createView();
        assertTrue("Очікувався об'єкт TextView", textView instanceof TextView);

        // Тестування табличної фабрики
        ViewFactory tableFactory = new TableViewFactory(50);
        ResultView tableView = tableFactory.createView();
        assertTrue("Очікувався об'єкт TableView", tableView instanceof TableView);
    }

    /**
     * Тестування перевантаження методів (Overloading).
     * Перевіряє можливість виклику перевантаженого методу viewBody з кастомним заголовком.
     */
    @Test
    public void testOverloading() {
        DataModel data = new DataModel(new double[]{0, 0, 0, 0}, 0);
        ResultView view = new TextView();

        // Перевіряємо, чи викликається перевантажений метод без помилок
        try {
            view.viewBody(data, "Тестовий заголовок історії");
            assertTrue(true);
        } catch (Exception e) {
            fail("Помилка при виклику перевантаженого методу viewBody: " + e.getMessage());
        }
    }
}
