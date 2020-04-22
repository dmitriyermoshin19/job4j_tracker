package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

public class StartUI {

    public void init(Input input, ITracker tracker, List<UserAction> actions, Consumer<String> output) {
        boolean run = true;
        while (run) {
            this.showMenu(actions, output);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions, Consumer<String> output) {
        output.accept("Menu.");
        int index = 0;
        for (UserAction act : actions) {
            output.accept(index++ + ". " + act.name());
        }
    }

    public static void main(String[] args) {
        Input validate = new ValidateInput(new ConsoleInput());
        ITracker tracker = new Tracker();
        List<UserAction> actions = List.of(
                new CreateAction("=== Create a new Item ===="),
                new FindAllAction("All items:"),
                new ReplaceAction("=== Edit item ==="),
                new DeleteAction("=== Delete item ==="),
                new FindByIdAction("=== Find item by Id ==="),
                new FindItemsByNameAction("=== Find items by name ==="),
                new ExitProgramAction("=== Exit Program ===")
                );
        new StartUI().init(validate, tracker, actions, System.out::println);
    }
}
