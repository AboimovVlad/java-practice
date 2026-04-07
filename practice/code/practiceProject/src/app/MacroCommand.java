package app;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас реалізує поняття "макрокоманда".
 * Дозволяє групувати декілька команд у список і виконувати їх як одну операцію.
 * Реалізує інтерфейс {@link Command}.
 *
 * @author Aboimov Vlad
 * @version 1.0
 */
public class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>();

    /**
     * Додає нову команду до списку макрокоманди.
     *
     * @param c об'єкт команди, що реалізує інтерфейс {@link Command}.
     */
    public void add(Command c) { commands.add(c); }

    /**
     * Послідовно виконує всі команди, що входять до макрокоманди.
     */
    @Override
    public void execute() {
        for (Command c : commands) c.execute();
    }

    /**
     * Скасовує всі команди макрокоманди.
     * Скасування відбувається у зворотному порядку до їх виконання,
     * щоб забезпечити коректне відновлення стану системи.
     */
    @Override
    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
