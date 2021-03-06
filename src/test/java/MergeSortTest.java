import Structures.Movie;
import Sort.MergeSort;
import Benchmark.OrderCheck;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSortTest {
    
    @Test
    public void testMergeSortInt() {
        ArrayList<Integer> mixed = new ArrayList<>(Arrays.asList(3, 8, 4, 12, 6, 5, 7, 10, 2));
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 10, 12));

        MergeSort.sort(mixed);
        assertArrayEquals(expected.toArray(), mixed.toArray());
    }

    @Test
    public void testMergeSortMovie() {
        Movie m1 = new Movie("Film 1", 1.0);
        Movie m2 = new Movie("Film 3", 3.0);
        Movie m3 = new Movie("Film 5", 5.0);
        Movie m4 = new Movie("Film 2", 2.0);
        Movie m5 = new Movie("Film 6", 6.0);
        Movie m6 = new Movie("Film 4", 4.0);

        ArrayList<Movie> mixed = new ArrayList<>();
        ArrayList<Movie> expected = new ArrayList<>();
        mixed.add(m1); expected.add(m1);
        mixed.add(m2); expected.add(m4);
        mixed.add(m3); expected.add(m2);
        mixed.add(m4); expected.add(m6);
        mixed.add(m5); expected.add(m3);
        mixed.add(m6); expected.add(m5);

        MergeSort.sort(mixed);
        assertArrayEquals(expected.toArray(), mixed.toArray());
    }

    @Test
    public void sortingTest() {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 10000);
        MergeSort.sort(movies);
        assertTrue(OrderCheck.isInCorrectOrder(movies));
    }
}
