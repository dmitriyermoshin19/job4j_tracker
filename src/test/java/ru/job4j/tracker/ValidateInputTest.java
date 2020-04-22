package ru.job4j.tracker;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {
    @Test
    public void whenInvalidInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"8", "1"})
        );
        input.askInt("Enter", 2);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Please select key from menu ")
                .toString();
        assertThat(new String(mem.toByteArray()), is(expect));
        System.setOut(out);
    }
}
