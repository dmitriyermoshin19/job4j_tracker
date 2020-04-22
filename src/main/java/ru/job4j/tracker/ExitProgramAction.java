package ru.job4j.tracker;

public class ExitProgramAction extends BaseAction {

    public ExitProgramAction(String name) {
        super(name);
    }
    @Override
    public String name() {
        return super.name();
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        return false;
    }
}
