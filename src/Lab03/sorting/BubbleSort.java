package Lab03.sorting;

import java.util.Collections;
import java.util.Comparator;

public class BubbleSort<E> implements ISort<E> {
    private int direction = 1;

    @Override
    public void sort(E[] array, Comparator<E> comparator, int direction) {
        this.direction = direction;
        int current = 0, walker;
        boolean flag = false;
        while (current < array.length && !flag) {
            walker = array.length - 1;
            flag = true;
            while (walker > current) {
                if (direction == 1) {
                    if (comparator.compare(array[walker], array[walker - 1]) < 0) {
                        flag = false;
                        E temp = array[walker - 1];
                        array[walker - 1] = array[walker];
                        array[walker] = temp;
                    }
                } else {
                    if (comparator.compare(array[walker], array[walker - 1]) > 0) {
                        flag = false;
                        E temp = array[walker - 1];
                        array[walker - 1] = array[walker];
                        array[walker] = temp;
                    }
                    walker--;
                }
                current++;
            }
        }
    }
}
