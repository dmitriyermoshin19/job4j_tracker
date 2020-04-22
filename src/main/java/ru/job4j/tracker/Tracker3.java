package ru.job4j.tracker;

import java.util.List;

public class Tracker3 {
    private static Item item = new Item("Single");
    private final Tracker tracker = new Tracker();

    private Tracker3() {
    }

    public static Item getItem() {
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
        Item result = tracker.findById(item.getId());
        String item1 = Tracker4.getItem().getName();
        System.out.println(result.getName() + "   " + item.getName() + "   " + item1);
    }
}
