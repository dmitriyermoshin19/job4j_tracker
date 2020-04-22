package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());

    /**
     * Метод создает подключение к базе данных по config.
     * @return результат подключения для теста.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app1.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS items("
                            + "id serial PRIMARY KEY, "
                            + "name VARCHAR(100) NOT NULL , "
                            + "description VARCHAR(200) NOT NULL);"
            );
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return this.connection != null;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO items(name, description) VALUES(?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(String.valueOf(generatedKeys.getInt("id")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE items SET name = ?, description = ? WHERE id = ?;")) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setInt(3, Integer.parseInt(id));
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM items WHERE id = ?;")) {
            ps.setInt(1, Integer.parseInt(id));
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM items;");
            while (rs.next()) {
                result.add(new Item(rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM items WHERE name = ?;")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Item(rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM items WHERE id = ?;")) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item = new Item(rs.getString("name"), rs.getString("description"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }
}
