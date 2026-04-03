package app;

import java.io.*;
import java.util.*;

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
}
