package ru.job4j.tracker;

import java.util.List;

public class FindAllAction extends BaseAction {

    public FindAllAction(String name) {
        super(name);
    }
    @Override
    public String name() {
        return super.name();
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        List<Item> items = tracker.findAll();
        for (Item item : items) {
            System.out.println(item.getId() + " " + item.getName());
        }
        return true;
    }
}
