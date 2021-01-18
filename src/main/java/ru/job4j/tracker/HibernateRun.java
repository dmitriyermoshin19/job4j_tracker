package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HibernateRun implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    public ItemH create(ItemH item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public void update(ItemH item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            ItemH item = new ItemH(null);
            item.setId(id);
            session.delete(item);
            session.getTransaction().commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ItemH> findAll() {
        List<ItemH> result = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.tracker.ItemH").list();
            session.getTransaction().commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  ItemH findById(Integer id) {
        ItemH result = new ItemH();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.get(ItemH.class, id);
            session.getTransaction().commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    public static void main(String[] args) {
        HibernateRun hr = new HibernateRun();
        try {
            ItemH itemH = hr.create(new ItemH("Learn Hibernate"));
            System.out.println(itemH);
            itemH.setName("Learn Hibernate 5.");
            hr.update(itemH);
            System.out.println(itemH);
            ItemH rsl = hr.findById(itemH.getId());
            System.out.println(rsl);
            hr.delete(rsl.getId());
            List<ItemH> list = hr.findAll();
            for (ItemH it : list) {
                System.out.println(it);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
