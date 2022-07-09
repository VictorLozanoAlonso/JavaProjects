import java.util.Scanner;
import java.io.*;

public class CharacterCounter {
    public static void main(String[] args) {
        //2D Array to cumulate and save each character occurrence
        int[][] lowerCase = new int[2][26];
        int[][] upperCase = new int[2][26];
        //Initialize and Preload the decimal value for each alphabet character(lower and upper case).
        for (int i = 0; i < 26; i++) {
            lowerCase[0][i] = 97 + i;
            lowerCase[1][i] = 0;
            upperCase[0][i] = 65 + i;
            upperCase[1][i] = 0;
        }
        System.out.print("Enter Filename: ");
        Scanner input = new Scanner(System.in);
        String filename = input.next();
        File file = new File(filename);
        try (FileReader fr = new FileReader(file)) {
            int character;
            //Reading the file character by character. Each coincidence will add one to the character counter.
            while ((character = fr.read()) != -1) {
                //Lowercase Alphabet
                if (character >= 97 && character <= 122) {
                    for (int i = 0; i < 26; i++) {
                        if (character == lowerCase[0][i])
                            lowerCase[1][i]++;
                    }
                }
                //Uppercase Alphabet
                if (character >= 65 && character <= 90) {
                    for (int i = 0; i < 26; i++) {
                        if (character == upperCase[0][i])
                            upperCase[1][i]++;
                    }
                }
            }
            fr.close();
            // Display the number of total characters found.
            // Only the count of characters found will be showed.
            for (int i = 0; i < 26; i++) {
                if (lowerCase[1][i] != 0)
                    System.out.println("Number of " + (char) lowerCase[0][i] + "'s: " + lowerCase[1][i]);
                if (upperCase[1][i] != 0)
                    System.out.println("Number of " + (char) upperCase[0][i] + "'s: " + upperCase[1][i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
