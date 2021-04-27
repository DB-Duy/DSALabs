package Lab05;

import java.util.List;

public class Queue<E> {
    private List<E> list = new DLinkedList<>();
    public void push(E e){
        list.add(e);
    }
    public E peek(){
        return list.get(0);
    }
    public void pop(){
        list.remove(0);
    }
}
