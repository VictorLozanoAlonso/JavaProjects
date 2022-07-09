import java.io.*;

public class Hangman {
    private String[] words;
    private int n_words;

    //Constructor with the filename argument.
    //It will load the words from the file to the array named words.
    Hangman(String filename) throws MinimumWordsException {
        try {
            int i = 0;
            String line;
            RandomAccessFile in = new RandomAccessFile(filename, "r");
            //Splitting the words and save them in the array
            while ((line = in.readLine()) != null) {
                words = line.split(" ");
            }
            if (words.length >= 10) {
                n_words = words.length;
                in.close();
            } else { //File with less than 10 words throws an exception
                in.close();
                throw new MinimumWordsException("File has less than 10 words");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MinimumWordsException e) {
            throw e;
        }
    }

    //Pick up a random word from the preloaded array words
    public String pickUpWord() {
        int i = ((int) (Math.random() * words.length));
        return this.words[i];
    }

    //Return the number of total words preloaded
    public int getNumWords() {
        return n_words;
    }

    //Write the file with the words and adding the new one asked to the user.
    //If the file has 10 words no one more will be added
    public void saveFile(String word, String filename) {
        try {
            int i = 0;
            FileWriter out = new FileWriter(filename);
            while (i < words.length) {
                out.write(words[i]);
                out.write(" ");
                i++;
            }
            if (!word.isEmpty())
                out.write(word);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
