package ru.job4j.tracker;
import java.util.*;


public class Tracker implements ITracker {
    /**
     * Коллекция для хранение заявок.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    @Override
    public Item findById(String id) {
        Item rst = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                rst = item;
                break;
            }
        }
        return rst;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean rst = false;
        int i = 0;
        for (Item itm : this.items) {
            if (itm.getId().equals(id)) {
                items.set(i, item);
                item.setId(id);
                rst = true;
                break;
            }
            i++;
        }
        return rst;
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> rst = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(name)) {
                rst.add(item);
            }
        }
        return rst;
    }

    @Override
    public List<Item> findAll() {
        return items;
    }

    @Override
    public boolean delete(String id) {
        boolean rst = false;
        int i = 0;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                items.remove(i);
                rst = true;
                break;
            }
            i++;
        }
        return rst;
    }
}
