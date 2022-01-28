package PuzzleSolvers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordleSolver {
    private HashMap<String, String> dictionary;

    public WordleSolver(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }
/**
 * 
 * @param characters : Input word in the format '_A_ER'
 * @param alphabet : Valid characters that COULD be in the word
 * @param characterPositionsMap : Table containing characters we know exist in the word and its position it is not located in
 * @param matches : List of matches that fits the criteria
 * @return
 */
    public List<String> findAllPossibleMatches(String characters, char[] alphabet, char[] guaranteedCharacters, HashMap<Integer, List<Character>> characterPositionsMap, List<String> matches) {
        char[] temp = characters.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == '_') {
                for (int j = 0; j < alphabet.length; j++) {
                    List<Character> charactersNotInPosition = characterPositionsMap.get(i+1);
                    if(!charactersNotInPosition.contains(alphabet[j])) {
                        temp[i] = alphabet[j];
                        String input = new String(temp);
                        findAllPossibleMatches(input, alphabet, guaranteedCharacters, characterPositionsMap, matches);
                    }
                }
            }
        }
        for(char c : guaranteedCharacters){
            if(!characters.contains(c + "")){
                return matches;
            }
        }
        if (dictionary.containsKey(characters)) {
            matches.add(characters);
        }
        return matches;
    }
}
