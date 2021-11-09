package container;

import entity.Machine;

import java.util.*;
import java.util.function.Predicate;

/**
 * This class is a container for {@code Machine} objects.
 */
public class MachineList implements List<Machine>{
    /** The size of the ArrayList (the number of elements it contains). */
    private int size;

    /**
     * The array buffer into which the elements of the MachineList are stored.
     * The capacity of the MachineList is the length of this array buffer.
     */
    private Machine[] machines;

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE / 50;

    /**
     * This list stores the conditions by which the iterator selects elements from the buffer.
     */
    private Predicate<Machine> predicates;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public MachineList(int initialCapacity) {
        if (initialCapacity >= 0) {
            machines = new Machine[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /** Constructs an empty list with an initial capacity of zero. */
    public MachineList(){
        machines = new Machine[0];
    }

    /**
     * Set {@code predicates}. It will be passed to the {@code IteratorForMachineList} constructor.
     *
     * @param predicates predicate
     */
    public void setPredicates(Predicate<Machine> predicates) {
        this.predicates = predicates;
    }

    /**
     * Return {@code size} of container. Number objects {@code Machine} in buffer {@code arrayMachine}.
     *
     * @return value fields {@code size}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an object of inner class {@code Itr}
     */
    @Override
    public Iterator<Machine> iterator() {
        if (predicates == null) {
            return new IteratorForMachineList(machine -> true);
        }
        return new IteratorForMachineList(predicates);
    }

    /**
     * Implementing the {@code iterator}.
     */
    private class IteratorForMachineList implements Iterator<Machine> {
        /** Index of element to be returned by subsequent call to next. */
        int cursor;

        /** Whether it is possible to delete the element pointed to by the cursor. */
        boolean canDelete;

        /** Predicate */
        final Predicate<Machine> predicates;

        /**
         * The constructor sets a list of conditions according to which the
         * elements of the buffer will be selected.
         *
         * @param predicates list of predicates
         */
        public IteratorForMachineList(Predicate<Machine> predicates) {
            this.predicates = predicates;
        }

        @Override
        public boolean hasNext() {
            int local = cursor;
            while (local < size){
                if (predicates.test(machines[cursor])) {
                    return true;
                }
                local++;
            }
            return false;
        }

        @Override
        public Machine next() {
            while (cursor < size) {
                if (predicates.test(machines[cursor])) {
                    canDelete = true;
                    return machines[cursor++];
                }
                cursor++;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (canDelete) {
                MachineList.this.remove(--cursor);
                canDelete = false;
            } else throw new IllegalStateException();
        }
    }

    /**
     * Returns buffer of this container ({@code arrayMachine})
     *
     * @return {@code arrayMachine}
     */
    @Override
    public Machine[] toArray() {
        return Arrays.copyOf(machines, size);
    }

    /**
     * Returns an array containing all the elements in this list in
     * proper sequence (from first to last element).
     *
     * @param a   an array into which the container objects will be placed
     * @param <T> type of array
     * @return returns a new array of container objects
     * @throws NullPointerException if the specified array is null
     * @throws ArrayStoreException  if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in this list
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(machines, size, a.getClass());
        System.arraycopy(machines, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param machine instance of {@code Machine}
     * @return {@code true} if element was added
     */
    @Override
    public boolean add(Machine machine) {
        if (isAdded(size + 1)) {
            machines[size++] = machine;
            return true;
        }
        return false;
    }

    /**
     * Increases the buffer size, fills free cells with null.
     *
     * @param minCapacity required size of the new buffer
     * @return {@code true} if buffer can be increased
     */
    private boolean isAdded(int minCapacity) {
        if ((minCapacity < 0) || (minCapacity == 0)) {
            return false;
        }
        if (minCapacity > machines.length) {
            increaseCapacity(minCapacity);
        }
        return true;
    }

    private void increaseCapacity(int minCapacity) {
        int oldCapacity = machines.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if ((newCapacity < 0) || (newCapacity > MAX_ARRAY_SIZE)) {
            newCapacity = MAX_ARRAY_SIZE;
        }
        machines = Arrays.copyOf(machines, newCapacity);
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @return {@code false} if the list does not contain the element, it is unchanged.
     */
    @Override
    public boolean remove(Object object) {
        int elementIndex = indexOf(object);
        if (elementIndex == -1) {
            return false;
        }
        fastRemove(elementIndex);
        return true;
    }

    private void fastRemove(int index) {
        int numberDeleteElement = (size - 1) - index;
        if (numberDeleteElement > 0)
            System.arraycopy(machines, index + 1, machines, index,
                    numberDeleteElement);
        machines[--size] = null;
    }

    /**
     * Returns {@code true} if this list contains all the elements of the
     * specified collection.
     *
     * @param  collection collection to be checked for containment in this list
     * @return {@code true} if this list contains all the elements of the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection)
            if (!contains(element))
                return false;
        return true;

    }

    /**
     * Appends all the elements in the specified collection to the end of
     * this list, in the order that they are returned by the
     * specified collection's Iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the operation
     * is in progress.  (This implies that the behavior of this call is
     * undefined if the specified collection is this list, and this
     * list is nonempty.)
     *
     * @param collection specified collection
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(Collection<? extends Machine> collection) {
        Objects.requireNonNull(collection ,"Argument must be not null");
        Object[] array = collection.toArray();
        int addedLength = array.length;
        if (isAdded(size + addedLength)) {
            System.arraycopy(array, 0, machines, size, addedLength);
            size += addedLength;
            return true;
        }
        return false;
    }

    /**
     * Inserts all the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.).
     *
     * @param index index at which to insert the first element from the specified collection
     * @param collection collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException the index does not meet the boundary condition
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(int index, Collection<? extends Machine> collection) {
        isIndexCorrect(index);
        Objects.requireNonNull(collection ,"Argument must be not null");
        Object[] a = collection.toArray();
        int addedLength = a.length;
        if (isAdded(size + addedLength)) {
            int numMoved = size - index;
            if (numMoved > 0)
                System.arraycopy(machines, index, machines, index + addedLength,
                        numMoved);
            System.arraycopy(a, 0, machines, index, addedLength);
            size += addedLength;
            return true;
        }
        return false;
    }

    private void isIndexCorrect(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("index must be from 0 to size");
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * @param collection collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     * @throws ClassCastException if the class of an element of this list
     *         is incompatible with the specified collection
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection is null
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        return removeIfContains(collection, false);
    }

    private boolean removeIfContains(Collection<?> collection, boolean complement) {
        final Object[] elementData = this.machines;
        int counterMachines = 0, counterCollection = 0;
        boolean modified = false;
        try {
            for (; counterMachines < size; counterMachines++)
                if (collection.contains(elementData[counterMachines]) == complement)
                    elementData[counterCollection++] = elementData[counterMachines];
        } finally {
            if (counterMachines != size) {
                System.arraycopy(elementData, counterMachines,
                        elementData, counterCollection,
                        size - counterMachines);
                counterCollection += size - counterMachines;
            }
            if (counterCollection != size) {
                for (int i = counterCollection; i < size; i++)
                    elementData[i] = null;
                size = counterCollection;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Opposite in meaning {@link MachineList#removeAll(Collection)}
     *
     * @throws ClassCastException if the class of an element of this list
     *         is incompatible with the specified collection
     * @throws NullPointerException if the specified collection is null
     * @see #contains(Object)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return removeIfContains(c, true);
    }

    /**
     * Removes all the elements from this list (optional operation).
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            machines[i] = null;
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public Machine get(int index) {
        isIndexCorrect(index);
        return machines[index];
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public Machine set(int index, Machine element) {
        isIndexCorrect(index);
        Machine oldValue = machines[index];
        machines[index] = element;
        return oldValue;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public void add(int index, Machine element) {
        isIndexCorrect(index);
        if (isAdded(size + 1)) {
            System.arraycopy(machines, index, machines, index + 1,
                    size - index);
            machines[index] = element;
            size++;
        }
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public Machine remove(int index) {
        isIndexCorrect(index);
        Machine oldValue = machines[index];
        fastRemove(index);
        return oldValue;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @return the index of the first occurrence of the specified element
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (machines[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(machines[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(object==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;object.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @return the index of the last occurrence of the specified element
     *         or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size-1; i >= 0; i--)
                if (machines[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (object.equals(machines[i]))
                    return i;
        }
        return -1;
    }

    /**
     * There is no functionality according to the terms of reference
     *
     * @return null
     */
    @Override
    public ListIterator<Machine> listIterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * There is no functionality according to the terms of reference
     *
     * @return null
     */
    @Override
    public ListIterator<Machine> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * There is no functionality according to the terms of reference
     *
     * @return null
     */
    @Override
    public List<Machine> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }
    
    @Override
    public  String toString() {
        if (machines == null)
            return "null";
        if (size == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(machines[i]);
            if (i == size)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}

