## Задание
2. Создать несколько потоков, обращающихся к общему списку объектов Integer.
   Потоки устанавливают и считывают значения из него, выводя результаты.
   Продемонстрировать работу потоков без использования и с использованием синхронизации.

```java
public static void main(String[] args) {
        SharedList sharedList = new SharedList();

        // without synchronized
        System.out.println("Запуск потоков без синхронизации:");
        Thread thread1 = new Thread(new ListWorker(sharedList, 10), "Поток 1");
        Thread thread2 = new Thread(new ListWorker(sharedList, 20), "Поток 2");
        Thread thread3 = new Thread(new ListWorker(sharedList, 30), "Поток 3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nЗапуск потоков с синхронизацией:");
        SharedList synchronizedSharedList = new SynchronizedSharedList();

        Thread thread4 = new Thread(new ListWorker(synchronizedSharedList, 10), "Поток 4");
        Thread thread5 = new Thread(new ListWorker(synchronizedSharedList, 20), "Поток 5");
        Thread thread6 = new Thread(new ListWorker(synchronizedSharedList, 30), "Поток 6");

        thread4.start();
        thread5.start();
        thread6.start();
    }
```

```txt
Запуск потоков без синхронизации:
Поток 1 добавил значение: 10
Поток 2 добавил значение: 20
Поток 3 добавил значение: 30
Поток 2 читает значения: [10, 30]
Поток 1 читает значения: [10, 30]
Поток 3 читает значения: [10, 30]

Запуск потоков с синхронизацией:
Поток 4 добавил значение: 10
Поток 4 читает значения: [10]
Поток 5 добавил значение: 20
Поток 5 читает значения: [10, 20]
Поток 6 добавил значение: 30
Поток 6 читает значения: [10, 20, 30]
```