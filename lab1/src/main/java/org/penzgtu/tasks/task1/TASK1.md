# Задание
1. Разработать класс с методами для установки и чтения значения переменной.
   Создать несколько потоков, которые устанавливают значение этой переменной,
   а затем считывают его, выводя информацию об установленном и считанном значениях.
   Продемонстрировать работу этих потоков.

```java
public class Main {
    public static void main(String[] args) {
        SharedVariable sharedVariable = new SharedVariable();

        Thread thread1 = new Thread(new VariableWorker(sharedVariable, 10), "Поток 1");
        Thread thread2 = new Thread(new VariableWorker(sharedVariable, 20), "Поток 2");
        Thread thread3 = new Thread(new VariableWorker(sharedVariable, 30), "Поток 3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
```

## Result
```txt
Поток 1 установил значение: 10
Поток 1 считал значение: 10
Поток 3 установил значение: 30
Поток 3 считал значение: 30
Поток 2 установил значение: 20
Поток 2 считал значение: 20
Поток 1 завершил работу с считанным значением: 10
Поток 3 завершил работу с считанным значением: 30
Поток 2 завершил работу с считанным значением: 20
```