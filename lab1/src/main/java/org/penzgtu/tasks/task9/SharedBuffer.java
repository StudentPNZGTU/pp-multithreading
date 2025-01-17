package org.penzgtu.tasks.task9;

import java.util.ArrayList;
import java.util.List;

public class SharedBuffer {
    private List<Integer> buffer = new ArrayList<>();
    private final static int MAX_SIZE = 5;

    public synchronized void add(int number) {
        while (buffer.size() >= MAX_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        buffer.add(number);
        System.out.println(Thread.currentThread().getName() + " добавил число: " + number);
        notifyAll();
    }
    public synchronized int remove() {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int number = buffer.remove(0);
        notifyAll();
        return number;
    }
}