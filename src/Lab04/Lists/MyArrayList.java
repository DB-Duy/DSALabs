package Lab04.Lists;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class MyArrayList<E> implements java.util.List<E> {
    private static enum MoveType {NEXT, PREV}

    ;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    //Internal data fields
    private E[] elements;
    private int size;

    //Constructor
    public MyArrayList(int capacity) throws IllegalArgumentException {
        if ((capacity < 0) || (capacity > MAX_CAPACITY)) {
            String message = String.format("Invalid capacity (=%d)",
                    capacity);
            throw new IllegalArgumentException(message);
        }
        this.elements = (E[]) new Object[capacity];
        this.size = 0;
    }

    public MyArrayList() throws IllegalArgumentException {
        this(10);
    }

    //Utility methods (private)
    private void checkValidIndex(int index, int min, int max) {/*here: code*/
        if ((index < min) || (index > max)) {
            String message = String.format("Invalid index (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }

    }

    private void checkCapacity(int minCapacity) {/*here: code*/
        if ((minCapacity < 0) || (minCapacity > MAX_CAPACITY))
            throw new OutOfMemoryError("Not enough memory to store the array");
        if (minCapacity < this.elements.length) return;
        else {
            //grow
            int oldCapacity = this.elements.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < 0)
                newCapacity = MAX_CAPACITY;
            this.elements = Arrays.copyOf(this.elements, newCapacity);
        }

    }

    //Group-1: read list’s properties
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    //Group-2: add elements
    public boolean add(E e) {/*here: code*/
        if (e == null) throw new NullPointerException("Can not add null pointer");
        checkCapacity(this.size + 1);

        this.elements[this.size++] = e;
        return true;
    }

    public void add(int index, E element) {/*here: code*/
        checkValidIndex(index, 0, size);
        if (element == null) throw new NullPointerException("Can not add null pointer");
        checkCapacity(this.size + 1);

        int copyCount = (this.size - 1) - index + 1;
        System.arraycopy(this.elements, index, this.elements, index + 1, copyCount);
        this.elements[index] = element;
        this.size++;
    }

    //Group-3: remove elements
    public E remove(int index) {/*here: code*/
        checkValidIndex(index, 0, size - 1);
        E oldElement = this.elements[index];
        int copyCount = (this.size - 1) - (index + 1) + 1;
        System.arraycopy(this.elements, index + 1, this.elements, index, copyCount);
        this.size--;
        return oldElement;

    }

    public boolean remove(Object o) {/*here: code*/
        int index = indexOf(o);
        if (index < 0) return false;
        remove(index);
        return true;

    }

    public void clear() {/*here: code*/ }

    //Group-4: set and get elements with indices
    public E get(int index) {/*here: code*/

        checkValidIndex(index, 0, size - 1);
        return this.elements[index];
    }

    public E set(int index, E element) {/*here: code*/
        checkValidIndex(index, 0, size - 1);
        E oldElement = this.elements[index];
        this.elements[index] = element;
        return oldElement;
    }

    //Grpup-5: map an object to its index + check object existing?
    public int indexOf(Object o) {/*here: code*/
        int foundIdx = -1;
        for (int idx = 0; idx < this.size; idx++) {
            if (this.elements[idx].equals(o)) { //== not
                foundIdx = idx;
                break;
            }
        }
        return foundIdx;

    }

    public int lastIndexOf(Object o) {/*here: code*/
        int foundIdx = -1;
        for (int idx = this.size - 1; idx >= 0; idx--) {
            if (this.elements[idx].equals(o)) {
                foundIdx = idx;
                break;
            }
        }
        return foundIdx;

    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    public boolean contains(Object o) {/*here: code*/
        boolean found = false;
        for (int idx = 0; idx < this.size; idx++) {
            if (this.elements[idx].equals(o)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    //Group-6: travel on lists


    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    //Supplementary functionalities


    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> c) {

    }


    //Inner Classes
    public class MyIterator implements Iterator<E> {
        int cursor = 0;
        MoveType moveType = MoveType.NEXT;
        boolean afterMove = false;

        @Override
        public boolean hasNext() {
            return this.cursor != MyArrayList.this.size;
        }

        @Override
        public E next() {

            cursor += 1;
            moveType = MoveType.NEXT;
            afterMove = true;
            return MyArrayList.this.elements[cursor - 1];

        }

        @Override
        public void remove() {
            if (!afterMove) return;
            MyArrayList.this.remove(cursor - 1);
            cursor -= 1;
            afterMove = false;

        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {

        }/*here: code*/
    }

    public class MyListIterator extends MyIterator implements ListIterator<E> {
        public MyListIterator(int index) {
            cursor = index;
        }

        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public E previous() {
            cursor -= 1;
            moveType = MoveType.PREV;
            afterMove = true;
            return MyArrayList.this.elements[cursor];
        }

        public int nextIndex() {
            return cursor;
        }

        public void add(E e) {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT)
                MyArrayList.this.add(cursor - 1, e);
            else
                MyArrayList.this.add(cursor, e);
            cursor += 1;
            afterMove = false;
        }

        public void remove() {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT) super.remove();
            else {
                MyArrayList.this.remove(cursor);
                afterMove = false;
            }
        }

        public void set(E e) {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT)
                MyArrayList.this.set(cursor - 1, e);
            else
                MyArrayList.this.set(cursor, e);
        }
    }
}
