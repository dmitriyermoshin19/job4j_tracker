package ru.job4j.tracker;
import java.util.Objects;

public class Item {
    private String id;
    private String name;
    private String description;

    public Item(String name) {
        this.name = name;
    }
    public Item(String name, String desc) {
        this.name = name;
        this.description = desc;
    }

    public Item(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
