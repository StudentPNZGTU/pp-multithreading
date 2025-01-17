package org.penzgtu.tasks.task6;

//public class FixedTask implements Runnable {
//    private boolean lockResource1First;
//
//    public FixedTask(boolean lockResource1First) {
//        this.lockResource1First = lockResource1First;
//    }
//
//    @Override
//    public void run() {
//        if (lockResource1First) {
//            synchronized (resource1) {
//                System.out.println(Thread.currentThread().getName() + " заблокировал resource1");
//                synchronized (resource2) {
//                    System.out.println(Thread.currentThread().getName() + " заблокировал resource2");
//                }
//            }
//        } else {
//            synchronized (resource1) {
//                System.out.println(Thread.currentThread().getName() + " заблокировал resource1");
//                synchronized (resource2) {
//                    System.out.println(Thread.currentThread().getName() + " заблокировал resource2");
//                }
//            }
//        }
//    }
//}