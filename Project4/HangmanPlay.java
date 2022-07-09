import java.lang.*;
import java.util.Scanner;

public class HangmanPlay {
    public static void main(String[] args) {
        try {
            char play = 'N';
            do {
                String runningWord = "";
                Scanner input = new Scanner(System.in);
                String userEntry, missedTries = "";
                Hangman data = new Hangman("hangman.txt");
                String word = data.pickUpWord();
                //Creating the variable that has the hidden word
                for (int i = 0; i < word.length(); i++)
                    runningWord += "*";
                do { //Loop to prompt the characters by the users
                    System.out.print("(Guess) Enter a letter in word " + runningWord + "  >> ");
                    userEntry = input.next();
                    if (userEntry.length() > 1)
                        System.out.println("\t" + "Bad entry. Type only one character");
                    else {
                        if (missedTries.contains(userEntry))
                            System.out.println("\t" + "You have already tried " + userEntry + ", try a new letter");
                        else if (runningWord.contains(userEntry))
                            System.out.println("\t" + userEntry + " is already in the word");
                        else {
                            if (word.contains(userEntry)) {
                                //Rebuilding the word with the characters found
                                for (int i = 0; i < word.length(); i++) {
                                    if (word.charAt(i) == userEntry.charAt(0)) {
                                        String aux = runningWord.substring(0, i) + userEntry + runningWord.substring(i + 1);
                                        runningWord = aux;
                                    }
                                }
                            } else {
                                //Missed tries will be tracked on "missedTries" String.
                                System.out.println("\t" + userEntry + " is not in the word");
                                missedTries += userEntry;
                            }
                        }
                    }
                } while (runningWord.contains("*")); //Working until all "*" are gone.
                System.out.print("\nThe word is " + runningWord);
                if (missedTries.length() > 0)
                    System.out.print(". You missed " + missedTries.length() + " times");
                String newWord = "";
                System.out.print("\nEnter a new word to be added in the memory > ");
                newWord = input.next();
                data.saveFile(newWord, "hangman.txt");
                System.out.print("\nDo you want to guess another word?\nEnter Y/y for yes or any other for Exiting > ");
                userEntry = input.next();
                play = userEntry.charAt(0);
            } while (play == 'Y' || play == 'y'); //Playing until the user type something different to "Y" or "y"
        } catch (Exception e) {
            System.out.println("\n    " + e);
        }
    }
}
