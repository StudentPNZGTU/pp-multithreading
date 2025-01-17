package org.penzgtu.tasks.task2;

public class ListWorker implements Runnable {
    private final SharedList sharedList;
    private final int valueToAdd;

    public ListWorker(SharedList sharedList, int valueToAdd) {
        this.sharedList = sharedList;
        this.valueToAdd = valueToAdd;
    }

    @Override
    public void run() {
        sharedList.addValue(valueToAdd);
        sharedList.printValues();
    }
}
