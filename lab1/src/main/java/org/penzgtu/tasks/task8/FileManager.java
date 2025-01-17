package org.penzgtu.tasks.task8;

import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private Map<Integer, String> fileData = new HashMap<>();

    public synchronized void writeData(int position, String data) {
        System.out.println(Thread.currentThread().getName() + " пытается записать данные в позицию " + position);

        while (fileData.containsKey(position)) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        fileData.put(position, data);
        System.out.println(Thread.currentThread().getName() + " записал '" + data + "' в позицию " + position);

        notifyAll();
    }

    public synchronized String readData(int position) {
        System.out.println(Thread.currentThread().getName() + " пытается прочитать данные из позиции " + position);

        while (!fileData.containsKey(position)) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        String data = fileData.get(position);
        System.out.println(Thread.currentThread().getName() + " прочитал '" + data + "' из позиции " + position);

        return data;
    }

}
