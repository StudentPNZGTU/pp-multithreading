package org.penzgtu.tasks.task7;

import java.util.ArrayList;
import java.util.List;

public class SharedFile {
    private List<String> content = new ArrayList<>();
    private boolean isWritten = false;

    public synchronized void writeData(String data) {
        while (isWritten) { // Если файл уже записан, ожидаем его чтения
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        content.add(data);
        System.out.println(Thread.currentThread().getName() + " записал: " + data);
        isWritten = true;
        notifyAll(); // Уведомляем другие потоки, что запись завершена
    }

    public synchronized void readData() {
        while (!isWritten) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " прочитал: " + content);
        isWritten = false;
        notifyAll(); // Уведомляем другие потоки, что чтение завершено
    }
}
