package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {
    private List<Item> result;

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("testNameFirst", "teatDescriptionFirst"));
            assertThat(tracker.findByName("testNameFirst").size(), is(1));
        }
    }

    @Test
    public void whenAddItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item second = new Item("testNameSecond", "teatDescriptionSecond");
            tracker.add(second);
            assertThat(tracker.findById(second.getId()).getName(), is("testNameSecond"));
        }
    }

    @Test
    public void whenReplaceNameOfItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item second = new Item("testNameSecond", "teatDescriptionSecond");
            Item third = new Item("testNameThird", "testDescriptionThird");
            tracker.add(second);
            tracker.replace(second.getId(), third);
            assertThat(tracker.findById(second.getId()).getName(), is("testNameThird"));
        }
    }

    @Test
    public void whenDeleteNameOfItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item second = new Item("testNameSecond", "teatDescriptionSecond");
            tracker.add(second);
            tracker.delete(second.getId());
            assertThat(tracker.findByName(second.getId()).size(), is(0));
        }
    }

    @Test
    public void whenFindAllItem() throws SQLException  {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item second = new Item("testNameSecond", "teatDescriptionSecond");
            Item third = new Item("testNameThird", "testDescriptionThird");
            tracker.add(second);
            tracker.add(third);
            result = tracker.findAll();
            assertThat(result.get(0).getDescription(), is("teatDescriptionSecond"));
        }
    }

    @Test
    public void whenFindItemByName() throws SQLException  {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item second = new Item("testNameSecond", "teatDescriptionSecond");
            Item third = new Item("testNameThird", "testDescriptionThird");
            tracker.add(second);
            tracker.add(third);
            result = tracker.findByName("testNameThird");
            assertThat(result.get(0).getDescription(), is("testDescriptionThird"));
        }
    }

    @Test
    public void whenFindItemById() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item second = new Item("testNameSecond", "teatDescriptionSecond");
            Item third = new Item("testNameThird", "testDescriptionThird");
            tracker.add(second);
            tracker.add(third);
            assertThat(tracker.findById(third.getId()).getDescription(), is("testDescriptionThird"));
        }
    }
}
