package org.penzgtu.tasks.task1;

public class VariableWorker implements Runnable {
    private final SharedVariable sharedVariable;
    private final int valueToSet;

    public VariableWorker(SharedVariable sharedVariable, int valueToSet) {
        this.sharedVariable = sharedVariable;
        this.valueToSet = valueToSet;
    }

    @Override
    public void run() {
        sharedVariable.setValue(valueToSet);

        int value = sharedVariable.getValue();
        System.out.println(Thread.currentThread().getName() + " завершил работу с считанным значением: " + value);
    }
}
