import java.sql.*;
import java.util.ArrayList;

import Benchmark.*;
import Structures.Movie;


public class Main {
    public static void main(String[] args) {

        DBDriver db = null;

        final int MAX = 1000000;

        int sizes[] = {10000, 50000, 100000, 200000, 500000, MAX};
        //String names[] = {"merge", "quick", "intro"};
        ArrayList<Movie> movies = null;

        try {
            db = new DBDriver();
            //db.clearDataBase();
            for(int i = 0; i < 3; i++) {
                for(int size: sizes) {
                    final ArrayList<Movie> unsortedMovies = Movie.readMoviesFromFile("src/main/resources/data.csv", size);
                    
                    movies = Movie.copyArray(unsortedMovies);
                    db.runSingleSorting(movies, "merge", size);
                    
                    movies = Movie.copyArray(unsortedMovies);
                    db.runSingleSorting(movies, "quick", size);
                    
                    movies = Movie.copyArray(unsortedMovies);
                    db.runSingleSorting(movies, "intro", size);
                }
            }
            for(int size: sizes) {
                System.out.println("MergeSort average time: " + db.getAverageTime("merge", size) + " for size: " + size);
                System.out.println("QuickSort average time: " + db.getAverageTime("quick", size) + " for size: " + size);
                System.out.println("IntroSort average time: " + db.getAverageTime("intro", size) + " for size: " + size);
                System.out.println();
            }
        }
        catch(SQLException e) {
            System.out.println("Unable to connect to database or handle a query");
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }
}
