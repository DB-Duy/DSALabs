package Lab03.sorting;

import java.util.Comparator;

public class StraightSelectionSort<E> implements ISort<E> {
    private int direction = 1;

    @Override
    public void sort(E[] array, Comparator<E> comparator, int direction) {
        this.direction = direction;
        int current = 0, smallest, walker;
        while (current < array.length - 1) {
            smallest = current;
            walker = current + 1;
            while (walker < array.length) {
                if (direction == 1) {
                    if (comparator.compare(array[walker], array[smallest]) < 0) {
                        smallest = walker;
                    }
                } else {
                    if (comparator.compare(array[walker], array[smallest]) > 0) {
                        smallest = walker;
                    }
                    walker++;
                }
                E temp = array[current];
                array[current] = array[smallest];
                array[smallest] = temp;
                current++;
            }
        }
    }
}
