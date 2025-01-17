package org.penzgtu.tasks.task6;

//public class Task2 implements Runnable {
//    @Override
//    public void run() {
//        synchronized (resource2) {
//            System.out.println("Task2 заблокировал resource2");
//            try {
//                Thread.sleep(100); // Пауза, чтобы гарантировать deadlock
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Task2 ожидает resource1");
//            synchronized (resource1) {
//                System.out.println("Task2 заблокировал resource1");
//            }
//        }
//    }
//}