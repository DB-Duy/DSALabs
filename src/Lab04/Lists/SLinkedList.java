package Lab04.Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SLinkedList<E> implements java.util.List<E> {
    private static enum MoveType {NEXT, PREV};

    private Node<E> head, tail;
    private int size;

    public SLinkedList() {/*here: code*/
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.next = head;
        size = 0;
    }

    //Utility methods (private)
    private void checkValidIndex(int index) {/*here: code*/
        if ((index < 0) || (index >= size)) {
            String message = String.format("Invalid index (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }
    }

    private Node<E> getDataNode(int index) {/*here: code*/
        checkValidIndex(index);
        Node<E> curNode = head.next;
        int runIndex = 0;
        while (curNode != tail) {
            if (index == runIndex) break;
            runIndex += 1;
            curNode = curNode.next;
        }
        return curNode;
    }

    private Node<E> getNode(int index) {
        if ((index < -1) || (index >= size)) {
            String message = String.format("Invalid index (including head) (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> curNode = head;
        int runIndex = -1;
        while (curNode != tail) {
            if (index == runIndex) break;
            runIndex += 1;
            curNode = curNode.next;
        }
        return curNode;
    }

    private void addAfter(Node<E> afterThis, Node<E> newNode) {/*here: code*/
        newNode.next = afterThis.next;
        afterThis.next = newNode;
        if (newNode.next == tail) tail.next = newNode;
        size += 1;

    }

    private Node<E> removeAfter(Node<E> afterThis) {/*here: code*/
        Node<E> removedNode = afterThis.next;
        afterThis.next = removedNode.next;
        if (removedNode.next == tail) tail.next = afterThis;
        removedNode.next = null;
        return removedNode;

    }

    //Group-1: read list???s properties
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    //Group-2: add elements
    public boolean add(E e) {/*here: code*/
        Node<E> newNode = new Node(null, e);
        Node<E> lastNode = tail.next;
        addAfter(lastNode, newNode);
        return true;
    }

    public void add(int index, E element) {/*here: code*/
        Node<E> prevNode = getNode(index - 1);
        Node<E> newNode = new Node<>(null, element);
        addAfter(prevNode, newNode);
    }

    //Group-3: remove elements
    public E remove(int index) {/*here: code*/
        if (size == 0) {
            String message = String.format("Remove at %d, but the list is empty", index);
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> prevNode = getNode(index - 1);
        Node<E> curNode = prevNode.next;
        removeAfter(prevNode);
        return curNode.element;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public boolean remove(Object o) {/*here: code*/
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

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {/*here: code*/ }

    @Override
    public E get(int index) {
        return getDataNode(index).element;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    //Group-4: set and get elements with indices


    //Inner Classes
    class Node<E> {/*here: code*/
        E element;
        Node<E> next;

        Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }

        void update(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }

    }

    class MyIterator implements Iterator<E> {
        Node<E> prev = SLinkedList.this.head;
        Node<E> cur = prev.next;

        int cursor = 0;
        SLinkedList.MoveType moveType = MoveType.NEXT;
        boolean afterMove = false;

        @Override
        public boolean hasNext() {
            return this.cursor != SLinkedList.this.size;
        }

        @Override
 /*
 Move cursor to next + return preivous element
 */
        public E next() {
            if(afterMove){
                prev = prev.next;
            }
            moveType= MoveType.NEXT;
            afterMove=true;
            cur = cur.next;
            cursor+=1;
            return prev.next.element;
        }

        @Override
        public void remove() {
            if (!afterMove) return;
            SLinkedList.this.remove(cursor - 1);
            cursor -= 1;
            afterMove = false;
            size--;
        }
    }

    class MyListIterator extends MyIterator implements ListIterator<E> {
        public MyListIterator(){};
        public MyListIterator(int index){
            //SLinkedList.this.checkValidIndex(index);
            prev=SLinkedList.this.getNode(index-1);
            cur=prev.next;
            cursor=index;
        }
        @Override
        public boolean hasPrevious() {
            return this.cursor!=0;
        }

        @Override
        public E previous() {
            moveType=MoveType.PREV;
            afterMove=true;
            prev = SLinkedList.this.getNode(cursor-2);
            cur=prev.next;
            cursor--;
            return cur.element;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor-1;
        }

        @Override
        public void remove() {
            if(!afterMove) return;
            if(moveType==MoveType.NEXT)
                super.remove();
            else{
                SLinkedList.this.remove(cursor);
                afterMove=false;
            }
        }

        @Override
        public void set(E e) {
            if(afterMove){
                prev.next.element=e;
            }
        }

        @Override
        public void add(E e) {
            if(afterMove){
                Node<E> newNode = new Node<>(null,e);
                addAfter(prev,newNode);
            }
        }/*here: code*/
    }


}

