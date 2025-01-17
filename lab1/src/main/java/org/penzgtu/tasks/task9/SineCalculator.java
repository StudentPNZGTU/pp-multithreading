package org.penzgtu.tasks.task9;

import java.util.ArrayList;
import java.util.List;

public class SineCalculator implements Runnable {
    private SharedBuffer sharedBuffer;
    private List<Double> results = new ArrayList<>();

    public SineCalculator(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int number = sharedBuffer.remove();
            double sine = Math.sin(Math.toRadians(number));
            results.add(sine);
            System.out.println(Thread.currentThread().getName() + " вычислил синус " + number + ": " + sine);
            try {
                Thread.sleep(150); // Pause 0.15
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Результаты синусов: " + results);
    }
}
