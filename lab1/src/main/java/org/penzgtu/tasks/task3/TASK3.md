## Задание
3. На основе второго задания продемонстрировать работу потоков с разными приоритетами
(использовать вывод информации о приоритете).

```java
public static void main(String[] args) {
        SharedList sharedList = new SharedList();

        Thread highPriorityThread = new Thread(new PriorityListWorker(sharedList, 10), "Поток с высоким приоритетом");
        Thread normalPriorityThread = new Thread(new PriorityListWorker(sharedList, 20), "Поток с нормальным приоритетом");
        Thread lowPriorityThread = new Thread(new PriorityListWorker(sharedList, 30), "Поток с низким приоритетом");

        highPriorityThread.setPriority(Thread.MAX_PRIORITY); // 10
        normalPriorityThread.setPriority(Thread.NORM_PRIORITY); // 5
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY); // 1

        highPriorityThread.start();
        normalPriorityThread.start();
        lowPriorityThread.start();

        try {
            highPriorityThread.join();
            normalPriorityThread.join();
            lowPriorityThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
```

```text
Поток с высоким приоритетом (приоритет: 10) добавил значение: 10
Поток с высоким приоритетом (приоритет: 10) читает значения: [10]
Поток с низким приоритетом (приоритет: 1) добавил значение: 30
Поток с низким приоритетом (приоритет: 1) читает значения: [10, 30]
Поток с нормальным приоритетом (приоритет: 5) добавил значение: 20
Поток с нормальным приоритетом (приоритет: 5) читает значения: [10, 30, 20]
```
