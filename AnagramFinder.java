import java.io.*;
import java.util.Iterator;

/**
 * COMS W3134 Final Project: Anagram Finder
 * @author David Kuang dk3260
 * @version 1.0 Dec 18 2023
 */

public class AnagramFinder {

    /**
     * Sorts characters in a String using insertion sort.
     * @param word is the input String to be sorted
     * @return String of input sorted by character's ASCII value
     */
    private static String insertionSort(String word) {
        char[] letters = word.toCharArray(); //converts input to char array

        for (int i = 1; i < letters.length; i++){
            char current = letters[i];
            for (int j = i - 1; j >= 0; j--){
                if (current < letters[j]){
                    letters[j + 1] = letters[j];
                    letters[j] = current;
                }
            }
        }

        return new String(letters); //converts char array back to String
    }

    /**
     * Lexicographically sorts Strings in a list.
     * @param words MyList of String values to be sorted
     * @return array of sorted Strings
     */
    private static String[] insertionSort(MyList<String> words) {
        String[] sorted = new String[words.size()];
        Iterator<String> wordIterator = words.iterator();

        //places first String in array to start off insertion sort
        sorted[0] = wordIterator.next();

        for (int i = 1; i < sorted.length; i++){
            String current = wordIterator.next();
            sorted[i] = current;
            for (int j = i - 1; j >= 0; j--){
                if (current.compareTo(sorted[j]) < 0){
                    sorted[j + 1] = sorted[j];
                    sorted[j] = current;
                }
            }
        }

        return sorted;
    }

    /**
     * Reads words from an input dictionary and stores them in a map.
     * @param dictMap map for words to be stored in
     * @param dictionary BufferedReader object made from dictionary file
     * @throws IOException - if I/O error while reading dictionary occurs
     */
    private static void dictOrganize(
            MyMap<String,MyList<String>> dictMap,
            BufferedReader dictionary
    ) throws IOException {
        try{
            String dictEntry; //stores a word within the dictionary

            while ((dictEntry = dictionary.readLine()) != null){
                String sortedWord = insertionSort(dictEntry.toLowerCase());

                MyList<String> wordList = dictMap.get(sortedWord);
                if (wordList == null){ //creates new value for a new key
                    wordList = new MyLinkedList<>();
                }
                wordList.add(dictEntry);
                dictMap.put(sortedWord, wordList);
            }
        }
        catch (IOException e){
            throw new IOException("reading error");
        }

    }

    /**
     * Using command line inputs, finds all anagrams of an input String that
     * exist within supplied dictionary file.
     * @param args - Exactly 3 will be taken: input word, dictionary file path, bst|avl|hash
     */
    public static void main(String[] args){
        try{
            if (args.length != 3) throw new ArrayIndexOutOfBoundsException();

            BufferedReader dictReader = new BufferedReader(new FileReader(args[1]));

            MyMap<String, MyList<String>> map; //initializes map variable

            switch(args[2]){ //instantiates map according to command line input
                case "bst" -> map = new BSTMap<>();
                case "avl" -> map = new AVLTreeMap<>();
                case "hash" -> map = new MyHashMap<>();
                default -> throw new IllegalArgumentException();
            }

            dictOrganize(map, dictReader); //stores contents of dictionary file into map

            dictReader.close();

            String input = args[0];
            String keyLookup = insertionSort(input.toLowerCase());
            MyList<String> anagrams = map.get(keyLookup);

            if (anagrams == null || (anagrams.size() ==  1 && input.equals(anagrams.get(0)))){
                System.out.println("No anagrams found.");
            }
            else{
                String[] anagramArray = insertionSort(anagrams);
                for (String anagram : anagramArray){
                    if (!anagram.equals(input)) System.out.println(anagram);
                }
            }

            System.exit(0);
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Usage: java AnagramFinder <word> <dictionary file> <bst|avl|hash>");
            System.exit(1);
        }
        catch(IllegalArgumentException e){
            System.err.println("Error: Invalid data structure '" + args[2] + "' received.");
            System.exit(1);
        }
        catch (IOException e){
            if (e.getMessage().equals("reading error")){
                System.err.println("Error: An I/O error occurred reading '" + args[1] + "'.");
            }
            else{
                System.err.println("Error: Cannot open file '" + args[1] + "' for input.");
            }
            System.exit(1);
        }
    }
}
