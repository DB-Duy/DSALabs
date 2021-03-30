package Lab03.sorting;

import java.util.Comparator;

public class StraightInsertionSort<E> implements ISort<E> {
    //Complexity of insertion sort O(N^2)
    //Depends on how sorted the array already is, is faster if the array is already partly sorted, and is slower if the array is completely unsorted and elements are far from each other
    private int direction = 1;

    @Override
    public void sort(E[] array, Comparator<E> comparator, int direction) {
        this.direction = direction;
        int current, walker;
        E temp;
        current = 1;
        while (current < array.length) {
            temp = array[current];
            walker = current - 1;
            if (direction == 1) {
                while ((walker >= 0) && comparator.compare(temp, array[walker]) < 0) {
                    array[walker + 1] = array[walker];
                    walker -= 1;
                }
            } else {
                while ((walker >= 0) && comparator.compare(temp, array[walker]) > 0) {
                    array[walker + 1] = array[walker];
                    walker -= 1;
                }
                array[walker + 1] = temp;
                current += 1;
            }
        }
    }
}