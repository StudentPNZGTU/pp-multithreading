# Задание
>Создать программу, в которой потоки обращаются к файлу, используя методы wait(), notify(), notifyAll().
Для разработки классов потоков использовать интерфейс Runnable.
```java
public class Main {
    public static void main(String[] args) {
        SharedFile sharedFile = new SharedFile();

        // Создаем потоки для записи и чтения данных
        Thread writerThread1 = new Thread(new Writer(sharedFile, "Данные 1"), "Writer 1");
        Thread writerThread2 = new Thread(new Writer(sharedFile, "Данные 2"), "Writer 2");
        Thread readerThread1 = new Thread(new Reader(sharedFile), "Reader 1");
        Thread readerThread2 = new Thread(new Reader(sharedFile), "Reader 2");

        // Запуск потоков
        writerThread1.start();
        readerThread1.start();
        writerThread2.start();
        readerThread2.start();
    }
}
```

```text
Writer 1 записал: Данные 1
Reader 2 прочитал: [Данные 1]
Writer 2 записал: Данные 2
Reader 1 прочитал: [Данные 1, Данные 2]
```