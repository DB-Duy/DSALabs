package Lab03.sorting;

import java.util.Comparator;

public class ShellSort<E> implements ISort<E> {
    private int[] segments;
    private int direction = 1;

    public ShellSort(int[] segments) {
        this.segments = segments;
    }

    @Override
    public void sort(E[] array, Comparator<E> comparator, int direction) {
        this.direction = direction;
        int i = 0;
        int k = segments[0];
        for (; i < segments.length; i++) {
            int segment = 1;
            while (segment <= k) {
                sortSegment(segment, k, array, comparator);
                segment++;
            }
            k = segments[i];
        }
    }

    public void sortSegment(int segment, int k, E[] array, Comparator<E> comparator) {
        int current = segment + k, walker;
        int count;
        E temp;
        while (current < array.length) {
            temp = array[current];
            walker = current - k;
            if (direction == 1) {
                while ((walker >= 0) && comparator.compare(temp, array[walker]) < 0) {
                    array[walker + k] = array[walker];
                    walker = walker - k;
                }
            } else {
                while ((walker >= 0) && comparator.compare(temp, array[walker]) > 0) {
                    array[walker + k] = array[walker];
                    walker = walker - k;
                }
                array[walker + k] = temp;
                current += k;
            }
        }
    }}
