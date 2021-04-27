package Lab05;

import Lab04.Lists.SLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DLinkedList<E> implements java.util.List<E> {

    private static enum MoveType{NEXT, PREV};
    private Node<E> head, tail;
    private int size;

    public class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> next, Node<E> prev, E element) {
            this.next = next;
            this.prev = prev;
            this.element = element;
        }

        public void update(Node<E> next, Node<E> prev, E element) {
            this.next = next;
            this.prev = prev;
            this.element = element;
        }
    }

    public DLinkedList() {
        head = new Node(tail, null, null);
        tail = new Node(null, head, null);
        head.next = tail; head.prev = null;
        tail.next = null; tail.prev = head;
        size = 0;
    }

    // utility methods

    private void checkValidIndex(int index) {
        if ((index < 0) | (index >= size)) {
            String message = String.format("Invalid index (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }
    }


    private Node<E> getDataNode(int index) {
        checkValidIndex(index);
        Node<E> curNode;
        int curIndex;
        if ((size - index) < (size/2)) {
            curNode = this.tail;
            curIndex = size;
            while (curIndex > index) {
                curIndex -= 1;
                curNode = curNode.prev;
            }
        }
        else {
            curNode = head.next;
            curIndex = 0;
            while (curIndex < index) {
                curIndex += 1;
                curNode = curNode.next;
            }
        }
        return curNode;
    }


    private Node<E> getNode(int index) {
        //checkValidIndex(index);
        Node<E> curNode;
        int curIndex;
        if ((size - index) < (size/2)) {
            curNode = this.tail;
            curIndex = size;
            while (curIndex > index) {
                curIndex -= 1;
                curNode = curNode.prev;
            }
        }
        else {
            curNode = head;
            curIndex = -1;
            while (curIndex < index) {
                curIndex += 1;
                curNode = curNode.next;
            }
        }
        return curNode;
    }


    // read list's properties

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    // add elements

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node(null, null, e);
        Node<E> lastNode = tail.prev;
        addAfter(lastNode, newNode);
        return true;
    }

    @Override
    public void add(int index, E element) {
        Node<E> prevNode = getNode(index - 1);
        Node<E> nextNode = getNode(index);
        Node<E> newNode = new Node(nextNode, prevNode, element);
        addBetween(prevNode, nextNode, newNode);
    }

    public void addBetween(Node<E> leftNode, Node<E> rightNode, Node<E> newNode) {
        leftNode.next = newNode;
        newNode.prev = leftNode;
        newNode.next = rightNode;
        rightNode.prev = newNode;
        if (newNode.next == tail) {
            tail.prev = newNode;
        }
        if (newNode.prev == head) {
            head.next = newNode;
        }
        size++;
    }

    public void addAfter(Node<E> afterThis, Node<E> newNode) {
        newNode.next = tail;
        newNode.prev = afterThis;
        afterThis.next = newNode;
        tail.prev = newNode;
        size++;
    }

    // remove elements
    @Override
    public boolean remove(Object o) {
        Node<E> prevNode = head;
        Node<E> curNode = head.next;
        boolean found = false;
        while (curNode != tail) {
            if (curNode.element.equals(o)) {
                found = true;
                removeAfter(prevNode);
                break;
            }
            curNode = curNode.next;
            prevNode = prevNode.next;
        }
        return found;
    }

    private Node<E> removeAfter(Node<E> afterThis) {
        Node<E> removedNode = afterThis.next;
        afterThis.next = removedNode.next;
        if (removedNode.next == tail) tail.next = afterThis;
        removedNode.next = null;
        return removedNode;
    }

    @Override
    public E remove(int index) {
        if (size == 0){
            String message = String.format("Remove at %d, but the list is empty", index);
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> prevNode = getNode(index - 1);
        Node<E> curNode = prevNode.next;
        removeAfter(prevNode);
        return curNode.element;
    }

    @Override
    public void clear() {
        Node<E> curNode = head.next;
        while (curNode != tail) {
            Node<E> temp = curNode;
            curNode = curNode.next;
            temp.update(null, null, null);
        }
        head.next = tail; head.prev = null;
        tail.next = null; tail.prev = head;
        size = 0;
    }


    // set and get elements

    @Override
    public E get(int index) {
        checkValidIndex(index);
        Node<E> curNode = getDataNode(index);
        return curNode.element;
    }

    @Override
    public E set(int index, E element) {
        checkValidIndex(index);
        Node<E> curNode = getDataNode(index);
        curNode.element = element;
        return curNode.element;
    }


    // map an object/check if object exists

    @Override
    public boolean contains(Object o) {
        boolean found = false;
        Node<E> curNode;
        int index = 0;
        while (index < size) {
            curNode = getDataNode(index);
            if (curNode.element.equals(o)) {
                found = true;
                break;
            }
            index++;
        }
        return found;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> curNode;
        int foundIndex = 0;
        int index = 0;
        while (index < size) {
            curNode = getDataNode(index);
            if (curNode.element.equals(o)) {
                foundIndex = index;
                break;
            }
            index++;
        }
        return foundIndex;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> curNode;
        int foundIndex = 0;
        int index = 0;
        while (index < size) {
            curNode = getDataNode(index);
            if (curNode.element.equals(o)) {
                foundIndex = index;
                break;
            }
            index++;
        }
        return foundIndex;
    }


    // Supplementary functionalities
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    // traverse list

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    public class MyIterator implements Iterator<E> {
        Node<E> prev = DLinkedList.this.head;
        Node<E> cur = prev.next;
        int cursor = 0;
        MoveType moveType = MoveType.NEXT;
        boolean afterMove = false;

        @Override
        public boolean hasNext() {
            return this.cursor != DLinkedList.this.size;
        }

        @Override
        /*
        Move cursor to next + return previous element
        */
        public E next() {
            if (afterMove) {
                prev = prev.next;
            }
            moveType = DLinkedList.MoveType.NEXT;
            afterMove = true;
            cur = cur.next;
            cursor += 1;
            return prev.next.element;
        }

        @Override
        public void remove() {
            if(!afterMove) return;
            DLinkedList.this.remove(cursor - 1);
            cursor -= 1;
            afterMove = false;
            size--;
        }
    }

    public class MyListIterator extends MyIterator implements ListIterator<E> {

        public MyListIterator(int index) {
            //DLinkedList.this.checkValidIndex(index);
            prev = DLinkedList.this.getNode(index - 1);
            cur = prev.next;
            cursor = index;
        }

        public MyListIterator() { }

        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        public int previousIndex() {
            return cursor -1;
        }

        public E previous() {
            moveType = MoveType.PREV;
            afterMove = true;
            prev = DLinkedList.this.getNode(cursor - 2);
            cur = prev.next;
            cursor -= 1;
            return cur.element;
        }

        public int nextIndex() {
            return cursor;
        }

        public void add(E e) {
            if (afterMove) {
                Node<E> newNode = new Node<E>(null, null, e);
                addBetween(cur, prev, newNode);
            }
        }

        public void remove() {
            if (!afterMove) return;
            if (moveType == DLinkedList.MoveType.NEXT) super.remove();
            else {
                DLinkedList.this.remove(cursor);
                afterMove = false;
            }
        }

        public void set(E e) {
            if (afterMove) {
                prev.next.element = e;
            }
        }
    }
}
