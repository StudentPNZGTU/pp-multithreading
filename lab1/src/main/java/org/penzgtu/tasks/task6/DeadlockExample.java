package org.penzgtu.tasks.task6;

public class DeadlockExample {

    // Два ресурса для блокировки
    private final static Object resource1 = new Object();
    private final static Object resource2 = new Object();

    // Поток, который блокирует resource1, затем пытается заблокировать resource2
    private static class Task1 implements Runnable {
        @Override
        public void run() {
            synchronized (resource1) {
                System.out.println("Task1 заблокировал resource1");
                try {
                    Thread.sleep(100); // Пауза, чтобы гарантировать deadlock
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task1 ожидает resource2");
                synchronized (resource2) {
                    System.out.println("Task1 заблокировал resource2");
                }
            }
        }
    }
}