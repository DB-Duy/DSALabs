package Lab05;

import java.util.List;

public class Stack<E> {
    private List<E> list = new DLinkedList<>();
    public Stack(){

    }
    public void push(E e){
        list.add(e);
    }
    public E peek(){
        return list.get(list.size()-1);
    }
    public void pop(){
        list.remove(list.size()-1);
    }
}
