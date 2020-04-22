package ru.job4j.tracker;

public class FindByIdAction extends BaseAction {

    public FindByIdAction(String name) {
        super(name);
    }

    @Override
    public String name() {
        return super.name();
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String id = input.askStr("Enter id: ");
        System.out.print(tracker.findById(id));
        return true;
    }
}
