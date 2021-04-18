package ru.job4j.tracker;

/**
 * интерфейс для реактивного программирования
 */
public interface Observe<T> {
    void receive(T model);
}
