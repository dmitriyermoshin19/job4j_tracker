package ru.job4j.tracker;

public class ReplaceAction extends BaseAction {

    public ReplaceAction(String name) {
        super(name);
    }
    @Override
    public String name() {
        return super.name();
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String id = input.askStr("Enter id: ");
        String name = input.askStr("Edit name: ");
        Item item = new Item(name);
        tracker.replace(id, item);
        System.out.print("Item edited");
        return true;
    }
}
