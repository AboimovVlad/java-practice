package app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Реалізація шаблону Worker Thread для управління чергою команд.
 */
public class CommandQueue implements Runnable {
    private final BlockingQueue<Command> queue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    /** Додає команду в чергу */
    public void put(Command cmd) {
        try {
            queue.put(cmd);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (running || !queue.isEmpty()) {
            try {
                Command cmd = queue.take(); // Блокує до появи команди
                cmd.execute();
                System.out.println("\n[Worker] Команду виконано паралельно.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stop() { running = false; }
}
