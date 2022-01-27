package PuzzleSolvers;

import java.util.ArrayList;
import java.util.HashMap;

public class AnagramSolver {
    private HashMap<String, String> dictionary;

    public AnagramSolver(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public ArrayList<String> allPossible(String word, boolean firstIteration) {
        ArrayList<String> matches = new ArrayList<String>();
        if (word.length() <= 1) {
            matches.add(word);
            return matches;
        }
        char c = word.charAt(0);
        ArrayList<String> substrings = allPossible(word.substring(1), false);
        for (String s : substrings) {
            for (int i = 0; i <= s.length(); i++) {
                String temp1 = s.substring(0, i);
                String temp2 = s.substring(i, s.length());
                String value = temp1 + c + temp2;
                if (firstIteration) {
                    if (dictionary.containsKey(value)) {
                        matches.add(value);
                    }
                } else {
                    matches.add(value);
                }
            }
        }
        return matches;
    }
}
