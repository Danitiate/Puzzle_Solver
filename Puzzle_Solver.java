import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

class Puzzle_Solver{
  static HashMap dictionary = new HashMap<String, String>();

  public static void main(String[] args) {
    try{
      File file = new File("Dictionary/enable1.txt");
      Scanner d = new Scanner(file);

      while(d.hasNext()){
        String word = d.nextLine();
        dictionary.put(word, word);
      }
    }catch(FileNotFoundException e){
      System.out.println(e);
      System.exit(0);
    }

    Scanner in = new Scanner(System.in);
    String word = "";

    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    while (true){
      System.out.println("\n1. ANAGRAM SOLVER");
      System.out.println("2. CROSSWORD SOLVER");
      System.out.println("3. CROSSWORD SOLVER (CUSTOM ALPHABET)");
      System.out.println("TYPE 'Q' TO QUIT\n");
      word = in.nextLine();
      if(word.matches("\\d+")){
        int value = Integer.parseInt(word);
        switch (value){

          case 1: solve(in.nextLine()); break;

          case 2: System.out.print("Word: ");
                  String word2 = in.nextLine(); solveX(word2, alphabet); break;

          case 3: System.out.print("Word: "); String word3 = in.nextLine();
                  System.out.print("Alphabet: "); String alpha = in.nextLine();
                  solveX(word3, alpha); break;

          default: System.out.println("UNKOWN COMMAND");
        }
      }else if(word.equals("Q") || word.equals("q")){
        break;
      }else{
        System.out.println("UNKOWN COMMAND");
      }
    }
  }

  public static ArrayList<String> printMatches(ArrayList<String> matches){
    //remove duplicates
    for(int i = 0; i < matches.size(); i++){
      String temp = matches.get(i);
      for(int j = i+1; j < matches.size(); j++){
        if(temp.equals(matches.get(j))){
          matches.remove(j);
          j--;
        }
      }
    }

    String out = "[";
    for(int i = matches.size()-1; i >= 0; i--){
      out += i == 0 ? matches.get(0) : matches.get(i) + ", ";
    }
    out += "]";
    System.out.println(out);
    return matches;
  }

  //Checks if a word exists in the dictionary
  public static boolean find(String word){
    word = word.toLowerCase();
    return dictionary.get(word) != null;
  }

//============================= ANAGRAM SOLVER ================================

  //Finds all char combinations and return the values that are found in the dictionary
  public static ArrayList<String> allPossible(String word, boolean first){
    ArrayList<String> retValue = new ArrayList<String>();
    if(word.length() <= 1){
      retValue.add(word);
      return retValue;
    }
    char c = word.charAt(0);
    ArrayList<String> substrings = allPossible(word.substring(1), false);

    for(String s : substrings){
      for(int i = 0; i <= s.length(); i++){
        String temp1 = s.substring(0, i);
        String temp2 = s.substring(i, s.length());
        String value = temp1 + c + temp2;
        if(first){
          if(find(value)){
            retValue.add(value);
          }
        }else{
          retValue.add(value);
        }
      }
    }

    return retValue;
  }


  public static ArrayList<String> solve(String word){
    if (word.length() > 10) return null;
    ArrayList<String> matches = allPossible(word, true);

    matches = printMatches(matches);
    return matches;
  }

//=========================== X-WORD SOLVER ===============================

  //Finds all values that matches the input string, where the wildcards are set in an alphabet
  public static ArrayList<String> findAll(String chars, char[] alpha, ArrayList<String> all){
    char[] temp = chars.toCharArray();

    for(int i = 0; i < temp.length; i++){
      if(temp[i] == '_'){
        for(int j = 0; j < alpha.length; j++){
          temp[i] = alpha[j];
          String input = new String(temp);
          findAll(input, alpha, all);
        }
      }
    }
    String value = new String(temp);
    ArrayList<String> retValue = new ArrayList<String>();
    if(find(value)){
      all.add(value);
    }

    return all;
  }

  public static ArrayList<String> solveX(String word, String alpha){
    ArrayList<String> words = findAll(word, alpha.toCharArray(), new ArrayList<String>());
    words = printMatches(words);
    return words;
  }
}
