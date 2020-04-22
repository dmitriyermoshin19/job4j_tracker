package ru.job4j.tracker;

public class DeleteAction extends BaseAction {

    public DeleteAction(String name) {
        super(name);
    }
    @Override
    public String name() {
        return super.name();
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String id = input.askStr("Enter id: ");
        tracker.delete(id);
        System.out.print("Enter id: ");
        return true;
    }
}
