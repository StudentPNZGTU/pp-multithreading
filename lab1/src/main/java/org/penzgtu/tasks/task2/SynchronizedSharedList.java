package org.penzgtu.tasks.task2;

public class SynchronizedSharedList extends SharedList {
    @Override
    public synchronized void addValue(int value) {
        super.addValue(value);
    }
    @Override
    public synchronized void printValues() {
        super.printValues();
    }
}
