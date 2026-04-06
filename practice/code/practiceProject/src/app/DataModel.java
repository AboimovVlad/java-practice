package app;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Клас для зберігання параметрів та результатів обчислень.
 * Реалізує інтерфейс {@link Serializable} для можливості збереження стану об'єкта.
 *
 * @author Абоімов Владислав
 * @version 1.0
 */
public class DataModel implements Serializable {
    /** Ідентифікатор версії серіалізації */
    private static final long serialVersionUID = 1L;

    /** Масив вхідних аргументів (кутів) */
    private double[] angles;
    /** Результат обчислень (кількість одиниць у двійковому представленні) */
    private int result;

    /**
     * Час проведення обчислень.
     * Поле позначено як transient, тому воно НЕ зберігається при серіалізації.
     */
    private transient Date calcTime;

    /**
     * Конструктор для створення нового об'єкта даних.
     *
     * @param angles масив вхідних кутів.
     * @param result отриманий результат обчислень.
     */
    public DataModel(double[] angles, int result) {
        this.angles = angles;
        this.result = result;
        this.calcTime = new Date();
    }

    /**
     * Повертає масив вхідних кутів.
     * @return масив double.
     */
    public double[] getAngles() { return angles; }
    /**
     * Повертає результат обчислень.
     * @return ціле число (кількість одиниць).
     */
    public int getResult() { return result; }
    /**
     * Повертає час обчислення.
     * @return об'єкт {@link Date} або null, якщо об'єкт було відновлено з файлу.
     */
    public Date getCalcTime() { return calcTime; }

    /**
     * Повертає текстове представлення об'єкта.
     * Враховує, що поле calcTime може бути null після десеріалізації.
     *
     * @return рядок з даними об'єкта.
     */
    @Override
    public String toString() {
        return "CalculationData{" +
                "arguments=" + Arrays.toString(angles) +
                ", result=" + result +
                ", transient averageValue (не серіалізується)=" + calcTime +
                '}';
    }
}
