# Задание
>Разработать программу для демонстрации ситуации взаимной блокировки (deadlock),
когда два потока обращаются к массиву значений, приводя к зависанию приложения.
Затем продемонстрировать, как этого избежать.
```java
public class Main {
    public static void main(String[] args) {
        // Запуск потоков, создающих deadlock
        System.out.println("Демонстрация deadlock:");
        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Исправление deadlock путем согласования порядка блокировки
        System.out.println("\nИзбежание deadlock:");

        Thread fixedThread1 = new Thread(new FixedTask(true));
        Thread fixedThread2 = new Thread(new FixedTask(false));

        fixedThread1.start();
        fixedThread2.start();
    }
}
```

```text

```