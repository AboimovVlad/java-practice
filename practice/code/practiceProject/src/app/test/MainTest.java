package app.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import app.DataModel;
import app.Solver;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Класс для тестування коректності вирахувань и серіалізації.
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
}
