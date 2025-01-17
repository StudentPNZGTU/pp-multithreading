package org.penzgtu;

import org.penzgtu.tasks.task9.NumberGenerator;
import org.penzgtu.tasks.task9.SharedBuffer;
import org.penzgtu.tasks.task9.SineCalculator;

public class Main {
    public static void main(String[] args) {
        SharedBuffer sharedBuffer = new SharedBuffer();

        // Creating threads
        Thread generatorThread = new Thread(new NumberGenerator(sharedBuffer), "Генератор чисел");
        Thread calculatorThread = new Thread(new SineCalculator(sharedBuffer), "Вычислитель синусов");

        generatorThread.start();
        calculatorThread.start();

        // waiting result
        try {
            generatorThread.join();
            calculatorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}