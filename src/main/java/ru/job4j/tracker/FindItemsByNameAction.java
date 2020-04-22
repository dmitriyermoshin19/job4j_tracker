package ru.job4j.tracker;

import java.util.List;

public class FindItemsByNameAction extends BaseAction {

    public FindItemsByNameAction(String name) {
        super(name);
    }

    @Override
    public String name() {
        return super.name();
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String name = input.askStr("Enter name: ");
        List<Item> items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println(item.getId() + " " + item.getName());
        }
        return true;
    }
}
