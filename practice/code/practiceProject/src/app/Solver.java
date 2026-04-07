package app;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Клас Solver відповідає за реалізацію обчислювальної логіки програми
 * та керування колекцією результатів обчислень.
 * Забезпечує обчислення індивідуального завдання, а також серіалізацію
 * та десеріалізацію списку результатів.
 *
 * @author Aboimov Vlad
 * @version 1.2
 */
public class Solver {
    /** Колекція результатів */
    private List<DataModel> history = new ArrayList<>();

    private static Solver instance;
    private Solver() {}

    public static synchronized Solver getInstance() {
        if (instance == null) {
            instance = new Solver();
        }
        return instance;
    }

    /** Виконує обчислення */
    public DataModel calculate(double[] angles) {
        double sum = 0;
        for (double a : angles) {
            sum += 1000 * Math.sin(a);
        }
        int avg = (int) (sum / angles.length);
        int onesCount = Integer.bitCount(Math.abs(avg));

        DataModel result = new DataModel(angles, onesCount);
        history.add(result);
        return result;
    }

    /** Видаляє останній доданий результат з історії */
    public void removeLast() {
        if (!history.isEmpty()) {
            history.remove(history.size() - 1);
        }
    }

    public List<DataModel> getHistory() {
        return history;
    }

    /** Логіка збереження */
    public void save(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(history);
        }
    }

    /** Логіка завантаження */
    @SuppressWarnings("unchecked")
    public void load(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            history = (List<DataModel>) ois.readObject();
        }
    }

    /** Демонстрація паралельної обробки: статистичні показники результатів */
    public void showStatistics() {
        if (history.isEmpty()) return;

        DoubleSummaryStatistics stats = history.parallelStream()
                .collect(Collectors.summarizingDouble(DataModel::getResult));

        System.out.println("\n--- Паралельна статистика результатів ---");
        System.out.println("Максимум: " + stats.getMax());
        System.out.println("Мінімум: " + stats.getMin());
        System.out.println("Середнє значення: " + stats.getAverage());
        System.out.println("Загальна кількість одиниць: " + stats.getSum());
    }
}
