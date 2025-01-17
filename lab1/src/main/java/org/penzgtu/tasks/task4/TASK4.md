## Задание
4. Разработать программу для сортировки массива методом «пузырька» с выводом промежуточных и конечных результатов,
   а также времени выполнения. Вычисления выполнять в отдельном потоке, а вывод результатов — в потоке-демоне.

```java
public static void main(String[] args) {
        int[] array = {5, 3, 8, 4, 2, 7, 1, 9, 6};
        Object lock = new Object();
        
        BubbleSortTask sortTask = new BubbleSortTask(array, lock);
        
        Thread sortingThread = new Thread(sortTask, "Сортировка");
        
        Thread daemonThread = new Thread(new ResultPrinterDaemon(sortTask, lock), "Демон");
        daemonThread.setDaemon(true); // installing daemon = true
   
        daemonThread.start();
        sortingThread.start();

        // Waiting for the sorting thread to finish
        try {
            sortingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
```

```text
Текущий массив: [3, 5, 4, 2, 7, 1, 8, 6, 9](daemon)
Текущий массив: [3, 4, 2, 5, 1, 7, 6, 8, 9](daemon)
Текущий массив: [3, 2, 4, 1, 5, 6, 7, 8, 9](daemon)
Текущий массив: [2, 3, 1, 4, 5, 6, 7, 8, 9](daemon)
Текущий массив: [2, 1, 3, 4, 5, 6, 7, 8, 9](daemon)
Текущий массив: [1, 2, 3, 4, 5, 6, 7, 8, 9](daemon)
Текущий массив: [1, 2, 3, 4, 5, 6, 7, 8, 9](daemon)
Время сортировки: 7001 мс
Текущий массив: [1, 2, 3, 4, 5, 6, 7, 8, 9](daemon)
```

### Потоки-демоны (Daemon Threads) в Java
>Поток-демон в Java - это поток с низким приоритетом, который работает в фоновом режиме для выполнения задач 
> обслуживания или предоставления сервисов для потоков пользователя (user threads).


### Основные характеристики потоков-демонов:
**Время жизни:**
- Поток-демон автоматически завершается, когда все пользовательские потоки завершили свою работу
- JVM завершится, когда останутся только потоки-демоны
### Применение:
- Сборка мусора (Garbage Collection)
- Фоновое сохранение данных
- Мониторинг системы
- Периодическое обслуживание кэша