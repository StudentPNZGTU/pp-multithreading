package org.penzgtu.tasks.task4;

import java.util.Arrays;

public class ResultPrinterDaemon implements Runnable {
    private BubbleSortTask sortTask;
    private final Object lock;

    public ResultPrinterDaemon(BubbleSortTask sortTask, Object lock) {
        this.sortTask = sortTask;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (lock) {
                    lock.wait(); // Waiting for notifications from the sorting stream
                    System.out.println("Текущий массив: " + Arrays.toString(sortTask.getArray()) + "(daemon)");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Поток-демон завершен.");
        }
    }
}