package org.penzgtu.tasks.task9;

import java.util.Random;

public class NumberGenerator implements Runnable {
    private SharedBuffer sharedBuffer;
    private Random random = new Random();

    public NumberGenerator(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int number = random.nextInt(100); // random num 0 to 99
            sharedBuffer.add(number);
            try {
                Thread.sleep(100); // Pause 0.1 sec
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
