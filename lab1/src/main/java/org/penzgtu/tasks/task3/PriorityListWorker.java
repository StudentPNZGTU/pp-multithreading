package org.penzgtu.tasks.task3;

public class PriorityListWorker implements Runnable {
    private final SharedList sharedList;
    private final int valueToAdd;

    public PriorityListWorker(SharedList sharedList, int valueToAdd) {
        this.sharedList = sharedList;
        this.valueToAdd = valueToAdd;
    }

    @Override
    public void run() {
        sharedList.addValue(valueToAdd);
        sharedList.printValues();
    }
}

