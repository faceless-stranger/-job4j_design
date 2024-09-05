package ru.job4j.gc.prof;
public interface Data {

    /*  Интерфейс класса который будет генерировать массив*/
    void insert(int elements);

    int[] getClone();
}