package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HbmTrackerTest {

    @Test
    public void testAdd() {
        HbmTracker ht = new HbmTracker();
        ItemH ih = new ItemH("item");
        ItemH itemH = ht.add(ih);
        assertThat(itemH.getName(), is("item"));
        List<ItemH> all = ht.findAll();
        assertEquals(itemH, all.get(0));
    }

    @Test
    public void testReplace() {
        HbmTracker ht = new HbmTracker();
        ItemH ih = new ItemH("item");
        ItemH itemH = ht.add(ih);
        ItemH ih1 = new ItemH("item1");
        ht.replace(itemH.getId(), ih1);
        List<ItemH> all = ht.findAll();
        assertEquals(ih1, all.get(0));
    }

    @Test
    public void testDelete() {
        HbmTracker ht = new HbmTracker();
        ItemH ih = new ItemH("item");
        ItemH itemH = ht.add(ih);
        ItemH ih1 = new ItemH("item1");
        ht.add(ih1);
        ht.delete(itemH.getId());
        List<ItemH> all = ht.findAll();
        assertEquals(ih1, all.get(0));
    }

    @Test
    public void testFindAll() {
        HbmTracker ht = new HbmTracker();
        ItemH ih = new ItemH("item");
        ItemH itemH = ht.add(ih);
        ItemH ih1 = new ItemH("item1");
        ht.add(ih1);
        assertEquals(List.of(itemH, ih1), ht.findAll());
    }

    @Test
    public void testFindByName() {
        HbmTracker ht = new HbmTracker();
        ItemH ih = new ItemH("item");
        ItemH itemH = ht.add(ih);
        List<ItemH> all = ht.findByName(itemH.getName());
        assertEquals(itemH, all.get(0));
    }

    @Test
    public void testFindById() {
        HbmTracker ht = new HbmTracker();
        ItemH ih = new ItemH("item");
        ItemH itemH = ht.add(ih);
        List<ItemH> all = ht.findAll();
        assertEquals(ht.findById(itemH.getId()), all.get(0));
    }
}