package Lab03.sorting;
import java.util.Comparator;

public interface ISort<E> {
    public void sort(E[] array, Comparator<E> comparator, int direction); // direction =1 means ascending, 0 means descending
}
