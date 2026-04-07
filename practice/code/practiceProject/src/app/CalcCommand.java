package app;

/**
 * Конкретна реалізація команди для виконання обчислень.
 * Реалізує інтерфейс {@link Command}, забезпечуючи зв'язок між параметрами
 * обчислення та об'єктом {@link Solver}.
 *
 * @author Aboimov Vlad
 * @version 1.0
 */
public class CalcCommand implements Command {
    private Solver solver;
    private double[] angles;
    private DataModel result;

    /**
     * Створює нову команду обчислення.
     *
     * @param solver екземпляр класу {@link Solver}, що виконує розрахунки.
     * @param angles масив вхідних значень для обчислення функції.
     */
    public CalcCommand(Solver solver, double[] angles) {
        this.solver = solver;
        this.angles = angles;
    }

    /**
     * Виконує обчислення за допомогою об'єкта {@link Solver}.
     * Отриманий результат автоматично зберігається в історії обчислювача.
     */
    @Override
    public void execute() {
        result = solver.calculate(angles);
    }

    /**
     * Скасовує дію команди, видаляючи останній результат обчислення
     * з історії об'єкта {@link Solver}.
     */
    @Override
    public void undo() {
        solver.removeLast();
    }
}
