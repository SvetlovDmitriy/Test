package container;

import entity.Lathe;
import entity.Machine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class MachineListTest {

    private Machine machine = new Lathe.Builder()
            .power(0)
            .weight(0)
            .model("Dip 300")
            .maxDiameter(300)
            .maxLengthDetail(4000)
            .thread(true).build();
    private Machine machine1 = new Lathe.Builder()
            .power(1)
            .weight(1)
            .model("1k62")
            .maxDiameter(250)
            .maxLengthDetail(2500)
            .thread(true).build();
    private Machine machine2 = new Lathe.Builder()
            .power(2)
            .weight(2)
            .model("Dip 200")
            .maxDiameter(200)
            .maxLengthDetail(2000)
            .thread(false).build();
    private MachineList listM;
    private Predicate<Machine> predicates;

    @Before
    public void init(){
        listM = new MachineList();
        listM.add(machine);
        listM.add(machine1);
        predicates = m -> ((m.getPower() > 0) && (m.getWeight() == 1));
    }

    @Test
    public void addIfListIsEmpty() {
        MachineList listM = new MachineList();
        listM.add(machine);
        Assert.assertEquals(listM.size(), 1);
    }

    @Test
    public void addByIndexIfListIsEmpty() {
        MachineList listM = new MachineList();
        listM.add(0, machine);
        Assert.assertEquals(listM.size(), 1);
    }

    @Test
    public void addByIndexIfAllOk() {
        listM.add(0, machine2);
        Assert.assertEquals(listM.size(), 3);
        Assert.assertEquals(listM.get(0), machine2);
        Assert.assertEquals(listM.get(1), machine);
        Assert.assertEquals(listM.get(2), machine1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexIfIndexNonCorrect() {
        listM.add(5, machine2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexIfIndexLower0() {
        listM.add(-3, machine2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexIfIndexHigherSize() {
        listM.add(4, machine2);
    }

    @Test
    public void getObjectIfAllOk() {
        Assert.assertEquals(listM.get(0), machine);
    }

    @Test
    public void getObjectIfNotAllOk() {
        Assert.assertNotEquals(listM.get(0), machine1);
    }

    @Test
    public void removeIfListIsEmpty() {
        MachineList listM = new MachineList();
        Assert.assertFalse(listM.remove(machine));
    }

    @Test
    public void removeIfExist() {
        listM.add(machine2);
        listM.remove(machine1);
        Assert.assertEquals(listM.size(), 2);
        Assert.assertEquals(listM.get(1), machine2);
    }

    @Test
    public void removeIfObjectNotInstanceOfMachine() {
        listM.remove(new Object());
        Assert.assertEquals(listM.size(), 2);
    }

    @Test
    public void removeIfNotExist() {
        listM.remove(machine2);
        Assert.assertEquals(listM.size(), 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexIfIndexNotCorrect() {
        listM.remove(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexIfIndexNotCorrectLower0() {
        listM.remove(-5);
    }

    @Test
    public void removeByIndexIfAllOk() {
        listM.add(machine2);
        listM.remove(1);
        Assert.assertEquals(listM.get(0), machine);
        Assert.assertEquals(listM.get(1), machine2);
        Assert.assertNull(listM.get(2));
    }

    @Test
    public void removeByIndexIfNotAllOk() {
        listM.add(machine2);
        listM.remove(1);
        Assert.assertEquals(listM.get(0), machine);
        Assert.assertNotEquals(listM.get(1), machine1);
        Assert.assertNull(listM.get(2));
    }

    @Test
    public void iteratorHasNextTrue() {
        Iterator<Machine> itr = listM.iterator();
        Assert.assertTrue(itr.hasNext());
    }

    @Test
    public void iteratorHasNextFalse() {
        Iterator<Machine> itr = listM.iterator();
        itr.next();
        itr.next();
        Assert.assertFalse(itr.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextFalse() {
        Iterator<Machine> itr = listM.iterator();
        itr.next();
        itr.next();
        itr.next();
    }

    @Test
    public void iteratorNextTrue() {
        Iterator<Machine> itr = listM.iterator();
        Assert.assertEquals(itr.next(), machine);
        Assert.assertEquals(itr.next(), machine1);
    }

    @Test(expected = IllegalStateException.class)
    public void deleteIteratorException() {
        Iterator<Machine> itr = listM.iterator();
        itr.remove();
    }

    @Test
    public void deleteIteratorTwoTime() {
        listM.add(machine2);
        listM.setPredicates(null);
        Iterator<Machine> itr = listM.iterator();
        itr.next();
        itr.remove();
        Assert.assertEquals(itr.next(), machine1);
        itr.remove();
        Assert.assertEquals(itr.next(), machine2);
        Assert.assertFalse(itr.hasNext());
    }

    @Test
    public void iteratorPredicateNextTrue() {
        listM.setPredicates(predicates);
        Iterator<Machine> itr = listM.iterator();
        Assert.assertEquals(itr.next(), machine1);
    }

    @Test
    public void iteratorPredicateNextFalse() {
        listM.setPredicates(predicates);
        Iterator<Machine> itr = listM.iterator();
        Assert.assertNotEquals(itr.next(), machine);
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorPredicateNextFalse2() {
        predicates = m -> ((m.getPower() > 6) && (m.getWeight() == 1));
        listM.setPredicates(predicates);
        Iterator<Machine> itr = listM.iterator();
        itr.next();
    }

    @Test
    public void iteratorPredicateHasNextFalse() {
        listM.setPredicates(predicates);
        Iterator<Machine> itr = listM.iterator();
        itr.hasNext();
        Assert.assertFalse(itr.hasNext());
    }

    @Test
    public void iteratorPredicateHasNextFalseOverBound() {
        listM.setPredicates(predicates);
        Iterator<Machine> itr = listM.iterator();
        itr.hasNext();
        itr.hasNext();
        itr.hasNext();
        Assert.assertFalse(itr.hasNext());
    }

    @Test
    public void iteratorPredicateHasNextTrue() {
        listM.setPredicates(predicates);
        Iterator<Machine> itr = listM.iterator();
        Assert.assertFalse(itr.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorPredicateNextError() {
        listM.setPredicates(predicates);
        Iterator<Machine> itr = listM.iterator();
        itr.next();
        itr.next();
    }
}