/**
 * Serenity Brown and Justin Girgis
 * CECS 277
 * Collections HW (PART 2)
 * 16 April 2020
 * The purpose of this homework is to implement our knowledge, or lack thereof of, TreeMaps and HashMaps.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class WarAndPeace {
    public static void main(String[] args) {
        File fileName = new File("WarAndPeace.txt");
        HashMap<String, Integer> hashMap = new HashMap<>();
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        System.out.println("\n///////PART 1 OF 2: HASH MAP TEST ///////"); // print out hash map test
        calcTime(fileName, hashMap);

        System.out.println("\n///////PART 2 OF 2: TREE MAP TEST ///////"); //print out tree map test
        calcTime(fileName, treeMap);

    }

    /**
     * This function will take in the file name and map. Then it will count the total words, add the unique words
     * to the map and increase the value by 1. Then, using the Instant and Duration class, it will print out the
     * total time. Lastly, the function will use an iterator to print out all the keys and their values.
     * @param fileName
     * @param map
     */
    public static void calcTime(File fileName, Map<String, Integer> map) {
        int totalWords = 0;
        Instant before = Instant.now();

        try (Scanner file = new Scanner(fileName)) {
            while (file.hasNext()) {
                int count = 1;
                String word = file.next().toLowerCase().replaceAll("[^a-z]", "");
                // delimiter
                totalWords++;
                if (!map.containsKey(word)) { // if map does not contain key, then place value in map
                    map.put(word, count);
                } else {
                    int counter = map.get(word); //retrieve word's value and
                    counter++;
                    map.replace(word, counter); // replace the word with the new int value
                }
            }// end while
            file.close();

            Instant after = Instant.now();
            Duration duration = Duration.between(before, after);
            long nano = duration.getNano();
            long milli = duration.toMillis();
            System.out.println("Time taken: " + milli + "  milliseconds or " + nano + " nanoseconds.");


            Set entrySet = map.entrySet();
            Iterator mapIterator = entrySet.iterator(); // iterator for our map

            while (mapIterator.hasNext()) {
                Map.Entry entry = (Map.Entry) mapIterator.next();
                System.out.print("{Key = " + entry.getKey() // print key and its value
                        + " Value = " + entry.getValue() + "}, ");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        System.out.println("\nTotal Words are: " + totalWords);

    }

}
