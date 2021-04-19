package Lab04.Question6;

import Lab04.Lists.MyArrayList;
import Lab04.Lists.SLinkedList;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MySLinkedListQuestion1and2 {
    public static void main(String[] args) {
        List<String> list = new SLinkedList<>();
        for (int idx = 0; idx < 10; idx++) {
            list.add("" + idx);
        }

        //(1)Print elements - Use Index, travel forward
        System.out.printf("%-32s", "Go forward, use index:");
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.printf("%s ", list.get(idx));
        }
        System.out.println();

        //(2)Print elements - Use Index, travel backward
        System.out.printf("%-32s", "Go backward, use index:");
        for (int idx = list.size() - 1; idx >= 0; idx--) {
            System.out.printf("%s ", list.get(idx));
        }
        System.out.println();

        //(3)Print elements - Use Iterator, travel forward
        System.out.printf("%-32s", "Go forward, use Iterator:");
        Iterator<String> it = list.listIterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.printf("%s ", item);
        }
        System.out.println();

        //(4)Print elements - Use ListIterator, travel forward
        System.out.printf("%-32s", "Go forward, use ListIterator:");
        ListIterator<String> fwd = list.listIterator();
        while (fwd.hasNext()) {
            String item = fwd.next();
            System.out.printf("%s ", item);
        }
        System.out.println();

        //(5)Print elements - Use ListIterator, travel backward
        System.out.printf("%-32s", "Go backward, use ListIterator:");
        ListIterator<String> bwd = list.listIterator(list.size());
        while (bwd.hasPrevious()) {
            String item = bwd.previous();
            System.out.printf("%s ", item);
        }
        System.out.println();
        question2();
    }

    public static void question2() {
        List<Integer> list = new MyArrayList<>();
        System.out.println("Question 2:");
        //Add elements
        for (int idx = 0; idx < 10; idx++) {
            list.add(idx);
        }

        //(1)Print elements - Use Index, travel forward
        System.out.printf("%-25s", "Before modification:");
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.printf("%s ", list.get(idx));
        }
        System.out.println();
        //(2)Remove odd numbers
        ListIterator<Integer> it = list.listIterator();
        while (it.hasNext()) {
            int item = it.next();
            if (item % 2 != 0) it.remove();
            else it.set(item * 10);
        }
        System.out.println("Answer: you shouldn't use a for loop instead of iterator because the loop removes elements from the list, making the size not a constant -> bugs");
        //(3) Print after changing
        System.out.printf("%-25s", "After modification:");
        it = list.listIterator();
        while (it.hasNext()) {
            System.out.printf("%s ", it.next());
        }
        System.out.println();
    }
}
