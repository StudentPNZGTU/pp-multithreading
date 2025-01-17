package org.penzgtu.tasks.task2;

import java.util.ArrayList;
import java.util.List;

public class SharedList {
    private final List<Integer> list = new ArrayList<>();

    public void addValue(int value) {
        list.add(value);
        System.out.println(Thread.currentThread().getName() + " добавил значение: " + value);
    }
    public void printValues() {
        System.out.println(Thread.currentThread().getName() + " читает значения: " + list);
    }
}
