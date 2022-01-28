package PuzzleSolvers;

import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class PuzzleSolver {
    public HashMap<Integer, HashMap<String, String>> dictionarySortedByLength = new HashMap<Integer, HashMap<String, String>>();

    public PuzzleSolver() {
        initializeDictionaryValues();
    }

    private void initializeDictionaryValues() {
        try {
            File file = new File("Dictionary/enable1.txt");
            Scanner dictionaryReader = new Scanner(file);
            while (dictionaryReader.hasNext()) {
                String word = dictionaryReader.nextLine();
                if (!dictionarySortedByLength.containsKey(word.length())) {
                    dictionarySortedByLength.put(word.length(), new HashMap<String, String>());
                }
                HashMap<String, String> dictionary = dictionarySortedByLength.get(word.length());
                dictionary.put(word, word);
                dictionarySortedByLength.put(word.length(), dictionary);
            }
            dictionaryReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public ArrayList<String> solveAnagram(String word) {
        if (word.length() > 11) {
            return new ArrayList<>();
        }
        AnagramSolver anagramSolver = new AnagramSolver(dictionarySortedByLength.get(word.length()));
        ArrayList<String> matches = anagramSolver.allPossible(word, true);
        matches = printMatches(matches);
        return matches;
    }

    public ArrayList<String> solveCrossword(String word, String alphabet) {
        CrosswordSolver crosswordSolver = new CrosswordSolver(dictionarySortedByLength.get(word.length()));
        ArrayList<String> words = crosswordSolver.findAll(word, alphabet.toCharArray(), new ArrayList<String>());
        words = printMatches(words);
        return words;
    }

    public ArrayList<String> solveWordle(String word, String alphabet, String characterPositions) {
        WordleSolver wordleSolver = new WordleSolver(dictionarySortedByLength.get(word.length()));
        HashMap<Integer, List<Character>> characterPositionsMap = populateCharacterPositionsMap(word, characterPositions);
        ArrayList<String> words = wordleSolver.findAllPossibleMatches(word, alphabet.toCharArray(), characterPositionsMap, new ArrayList<String>());
        words = printMatches(words);
        return words;
    }

    private HashMap<Integer, List<Character>> populateCharacterPositionsMap(String word, String characterPositions) {
        HashMap<Integer, List<Character>> characterPositionsMap = new HashMap<Integer, List<Character>>();
        if (characterPositions.length() > 0) {
            for (int i = 1; i <= word.length(); i++) {
                String[] characters = characterPositions.split(i + "");
                List<Character> charactersNotAtIndex = new ArrayList<Character>();
                for (String s : characters) {
                    char c = s.charAt(s.length() - 1);
                    if (Character.isLetter(c)) {
                        charactersNotAtIndex.add(c);
                    }
                }
                characterPositionsMap.put(i, charactersNotAtIndex);
            }
        }
        return characterPositionsMap;
    }

    private ArrayList<String> printMatches(ArrayList<String> matches) {
        // remove duplicates
        for (int i = 0; i < matches.size(); i++) {
            String temp = matches.get(i);
            for (int j = i + 1; j < matches.size(); j++) {
                if (temp.equals(matches.get(j))) {
                    matches.remove(j);
                    j--;
                }
            }
        }
        String output = "[";
        for (int i = matches.size() - 1; i >= 0; i--) {
            output += i == 0 ? matches.get(0) : matches.get(i) + ", ";
        }
        output += "]";
        System.out.println(output);
        return matches;
    }
}
