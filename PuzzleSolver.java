import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

class PuzzleSolver {
    public static HashMap<Integer, HashMap<String, String>> dictionarySortedByLength = new HashMap<Integer, HashMap<String, String>>();

    public static void main(String[] args) {
        initializeDictionaryValues();
        initializeMainMenu();
    }

    private static void initializeDictionaryValues() {
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

    private static void initializeMainMenu() {
        Scanner inputScanner = new Scanner(System.in);
        String userInput = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while (true) {
            System.out.println("\n1. ANAGRAM SOLVER");
            System.out.println("2. CROSSWORD SOLVER");
            System.out.println("3. CROSSWORD SOLVER (CUSTOM ALPHABET)");
            System.out.println("TYPE 'Q' TO QUIT\n");
            userInput = inputScanner.nextLine();
            if (userInput.matches("\\d+")) {
                int value = Integer.parseInt(userInput);
                switch (value) {
                    case 1:
                        System.out.print("Word: ");
                        solveAnagram(inputScanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Word (Use '_' for wildcards): ");
                        userInput = inputScanner.nextLine();
                        solveCrossword(userInput, alphabet);
                        break;
                    case 3:
                        System.out.print("Word (Use '_' for wildcards): ");
                        userInput = inputScanner.nextLine();
                        System.out.print("Alphabet (Only check for words containing these characters): ");
                        String customAlphabet = inputScanner.nextLine().toUpperCase();
                        solveCrossword(userInput, customAlphabet);
                        break;
                    default:
                        System.out.println("UNKNOWN COMMAND");
                }
            } else if (userInput.equals("Q") || userInput.equals("q")) {
                inputScanner.close();
                break;
            } else {
                System.out.println("UNKNOWN COMMAND");
            }
            userInput = "";
        }
    }

    public static ArrayList<String> printMatches(ArrayList<String> matches) {
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

    public static ArrayList<String> solveAnagram(String word) {
        if(word.length() > 11) {
            return new ArrayList<>();
        }
        AnagramSolver anagramSolver = new AnagramSolver(dictionarySortedByLength.get(word.length()));
        ArrayList<String> matches = anagramSolver.allPossible(word, true);
        matches = printMatches(matches);
        return matches;
    }

    public static ArrayList<String> solveCrossword(String word, String alphabet) {
        CrosswordSolver crosswordSolver = new CrosswordSolver(dictionarySortedByLength.get(word.length()));
        ArrayList<String> words = crosswordSolver.findAll(word, alphabet.toCharArray(), new ArrayList<String>());
        words = printMatches(words);
        return words;
    }
}
