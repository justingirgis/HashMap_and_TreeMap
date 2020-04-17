/**
 * Serenity Brown and Justin Girgis
 * CECS 277
 * Collections HW (PART 1)
 * 16 April 2020
 * The purpose of this homework is to implement our knowledge, or lack thereof of, TreeMaps and HashMaps.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Scrabble {

    public static void main(String[] args) {
    // reading the ScrabblePoints.txt file
        File fileName = new File("ScrabblePoints.txt");
        HashMap<String,Integer> map = new HashMap<>(getScrabbleValue(fileName));
        System.out.print(map); // test to see that our hash map is correct!

        fileName = new File("Qwords.txt");
        getScore(fileName, map);

    }

    /**
     * This function will take in the file name as a parameter, create a hashmap of the value of each alphabetic charcter,
     * and then it returns the hash map.
     * @param fileName
     * @return
     */
    public static HashMap<String, Integer> getScrabbleValue(File fileName) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        try(Scanner file = new Scanner(fileName)){
            while (file.hasNext()){
                // read in each character as a string and convert first element into an integer
                String[] arr = file.nextLine().split(" ");
                int val = Integer.parseInt(arr[0]); // changed first index in line into an integer, becomes value
                for(int i = 1; i < arr.length; i++) { // loop through remainder of the array and put it into hashmap key
                    String letters = arr[i];
                    hashMap.put(letters, val);
                } // end for
            } // end while
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found!");
        }

        return hashMap;
    }

    /**
     * This function will take in both the file name and hash map as a parameter and then it will save every char of every word
     * and then get how many of each key is used in the Qwords file.
     * @param fileName
     */
    public static void getScore(File fileName, HashMap<String, Integer> hashMap) {

        try(Scanner file = new Scanner(fileName)){
            while (file.hasNextLine()){
                // read in each word and calculate scrabble value
                int amount = 0;
                String string = file.nextLine();
                //System.out.println(string);

                for(int i = 0; i < string.length(); i++) { // loop through remainder of the array and put it into hashmap key
                    char letter = Character.toUpperCase(string.charAt(i));
                    String word = Character.toString(letter);

                    amount += hashMap.get(word);
                } // end for

                System.out.println("Value of word " + string + " is " + amount);

            } // end while
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }
}
