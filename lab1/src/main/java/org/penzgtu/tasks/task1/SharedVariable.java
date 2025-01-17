package org.penzgtu.tasks.task1;

public class SharedVariable {
    private int value;

    // synchronized setter
    public synchronized void setValue(int value) {
        this.value = value;
        System.out.println(Thread.currentThread().getName() + " установил значение: " + value);
    }

    // synchronized getter
    public synchronized int getValue() {
        System.out.println(Thread.currentThread().getName() + " считал значение: " + value);
        return value;
    }
}
