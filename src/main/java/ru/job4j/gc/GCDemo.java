package ru.job4j.gc;

public class GCDemo {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();    /* возвращает количество свободной памяти в байтах  */
        final long totalMemory = ENVIRONMENT.totalMemory();  /* возвращает общее количество памяти которое может быть использовано  */
        final long maxMemory = ENVIRONMENT.maxMemory();      /* возвращает максимальное количество памяти которое может быть использовано */
        System.out.println("=== Environment state ===");
        System.out.printf("Свободно памяти: %d%n", freeMemory / MB);
        System.out.printf("Сейчас выделено : %d%n", totalMemory / MB);
        System.out.printf("Максимальное: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 100; i++) {
            new Person(i, "N" + i);
        }
        System.gc();
        info();
    }

}