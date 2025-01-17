# Задание
>Разработать программу для создания файла, записи данных и их чтения.
Обеспечить корректную работу нескольких экземпляров, чтобы запись в конкретную позицию разрешалась
только одному экземпляру.
```java
public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        
        Thread writer1 = new Thread(new FileWriterTask(fileManager, 1, "Данные 1"), "Writer 1");
        Thread writer2 = new Thread(new FileWriterTask(fileManager, 2, "Данные 2"), "Writer 2");
        Thread writer3 = new Thread(new FileWriterTask(fileManager, 1, "Данные 3 (Конкурент)"), "Writer 3"); // Пытается записать в ту же позицию, что и writer1
        
        Thread reader1 = new Thread(new FileReaderTask(fileManager, 1), "Reader 1");
        Thread reader2 = new Thread(new FileReaderTask(fileManager, 2), "Reader 2");
        
        writer1.start();
        reader1.start();
        writer2.start();
        reader2.start();
        writer3.start();
        
        try {
            writer1.join();
            writer2.join();
            writer3.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Все операции завершены.");
    }
}
```

```text

```