import java.util.Scanner;
import java.util.stream.Stream;
import PuzzleSolvers.PuzzleSolver;

class PuzzleSolverProgram {
    
    public static void main(String[] args) {
        PuzzleSolver puzzleSolver = new PuzzleSolver();
        mainMenu(puzzleSolver);
    }

    private static void mainMenu(PuzzleSolver puzzleSolver) {
        Scanner inputScanner = new Scanner(System.in);
        String userInput = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while (true) {
            System.out.println("\n1. ANAGRAM SOLVER");
            System.out.println("2. CROSSWORD SOLVER");
            System.out.println("3. CROSSWORD SOLVER (CUSTOM ALPHABET)");
            System.out.println("4. WORDLE SOLVER");
            System.out.println("TYPE 'Q' TO QUIT\n");
            userInput = inputScanner.nextLine();
            if (userInput.matches("\\d+")) {
                int value = Integer.parseInt(userInput);
                switch (value) {
                    case 1:
                        System.out.print("Word: ");
                        puzzleSolver.solveAnagram(inputScanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Word (Use '_' for wildcards): ");
                        userInput = inputScanner.nextLine().toUpperCase();
                        puzzleSolver.solveCrossword(userInput, alphabet);
                        break;
                    case 3:
                        System.out.print("Word (Use '_' for wildcards): ");
                        userInput = inputScanner.nextLine().toUpperCase();
                        System.out.print("Alphabet (Only check for words containing these characters): ");
                        String customAlphabet = inputScanner.nextLine().toUpperCase();
                        puzzleSolver.solveCrossword(userInput, customAlphabet);
                        break;
                    case 4:
                        System.out.print("Word (Use '_' for wildcards): ");
                        userInput = inputScanner.nextLine().toUpperCase();
                        System.out.print("Excluded characters: ");
                        String excludedAlphabet = inputScanner.nextLine().toUpperCase();
                        excludedAlphabet = alphabet.replaceAll("[" + excludedAlphabet + "]", "");
                        System.out.print("Guaranteed characters: ");
                        String guaranteedCharacters = inputScanner.nextLine().toUpperCase();
                        puzzleSolver.solveWordle(userInput, excludedAlphabet, guaranteedCharacters);
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
}
