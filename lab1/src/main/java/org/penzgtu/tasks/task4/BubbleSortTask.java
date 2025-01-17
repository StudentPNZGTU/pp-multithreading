package org.penzgtu.tasks.task4;

public class BubbleSortTask implements Runnable {
    private int[] array;
    private final Object lock;

    public BubbleSortTask(int[] array, Object lock) {
        this.array = array;
        this.lock = lock;
    }

    @Override
    public void run() {
        boolean swapped;
        long startTime = System.currentTimeMillis();

        // Bubble Sort
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            synchronized (lock) {
                lock.notify(); // Notification intermediate result
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!swapped) break; // if array sorted
        }

        synchronized (lock) {
            System.out.println("Время сортировки: " + (System.currentTimeMillis() - startTime) + " мс");
            lock.notify(); // notify daemon-thread
        }
    }

    public int[] getArray() {
        return array;
    }
}
