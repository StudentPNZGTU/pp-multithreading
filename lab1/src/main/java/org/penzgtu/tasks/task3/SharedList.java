package org.penzgtu.tasks.task3;

import java.util.ArrayList;
import java.util.List;

public class SharedList {
    private final List<Integer> list = new ArrayList<>();
    public synchronized void addValue(int value) {
        list.add(value);
        System.out.println(Thread.currentThread().getName() + " (приоритет: " + Thread.currentThread().getPriority() +
                ") добавил значение: " + value);
    }
    public synchronized void printValues() {
        System.out.println(Thread.currentThread().getName() + " (приоритет: " + Thread.currentThread().getPriority() +
                ") читает значения: " + list);
    }
}
