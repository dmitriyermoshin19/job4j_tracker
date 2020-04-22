package ru.job4j.tracker;

import java.util.List;

public class Tracker2 {
    private static Item item;
    private final Tracker tracker = new Tracker();
    private Tracker2() {
    }

    public static Item getItem() {
        if (item == null) {
            item = new Item("Single");
        }
        return item;
    }
    public Item add(Item item) {
        return tracker.add(item);
    }
    public Item findById(String id) {
        return tracker.findById(id);
    }
    public boolean replace(String id, Item item) {
        return tracker.replace(id, item);
    }
    public List<Item> findByName(String name) {
        return tracker.findByName(name);
    }
    public List<Item> findAll() {
        return tracker.findAll();
    }
    public boolean delete(String id) {
        return tracker.delete(id);
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item item1 = new Item("test2");
        tracker.add(item1);
        Item result = tracker.findById(item1.getId());
        System.out.println(result.getName() + "   " + item.getName());
    }
}
