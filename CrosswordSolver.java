import java.util.ArrayList;
import java.util.HashMap;

public class CrosswordSolver {
    private HashMap<String, String> dictionary;

    public CrosswordSolver(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Finds all values that matches the input string, where the wildcards are set in an alphabet
     */ 
    public ArrayList<String> findAll(String chars, char[] alphabet, ArrayList<String> allMatches) {
        char[] temp = chars.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == '_') {
                for (int j = 0; j < alphabet.length; j++) {
                    temp[i] = alphabet[j];
                    String input = new String(temp);
                    findAll(input, alphabet, allMatches);
                }
            }
        }
        String value = new String(temp);
        if (dictionary.containsKey(value)) {
            allMatches.add(value);
        }
        return allMatches;
    }
}
