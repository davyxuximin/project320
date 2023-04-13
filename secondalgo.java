import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;


public class secondalgo {
    
    // This HashMap stores key-value pairs from a dataset file.
    public static HashMap<String, String> abbe = new HashMap<>();
    
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String twitter = sc.nextLine();
            dataset();
            System.out.println(twitter(twitter));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    // This method reads key-value pairs from a file and stores them in a HashMap.
    public static void dataset() throws FileNotFoundException {
        File file = new File("/Users/xuximin/Desktop/320/dataset.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            StringBuilder key = new StringBuilder();
            StringBuilder value = new StringBuilder();
            int i = 0;
            for (; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '\t') {
                    i++;
                    break;
                }
                key.append(c);
            }
            for (; i < line.length(); i++) {
                char c = line.charAt(i);
                value.append(c);
            }
            abbe.put(key.toString(), value.toString());
        }
    }

    // This method processes an input string by looking up each word in the HashMap and replacing it with its value.
    public static String twitter(String twitter) {
        twitter = twitter.replaceAll("[\\pP‘'“”]", "");
        StringBuilder sb = new StringBuilder();
        String newSentence = "";
        StringBuilder currentWord = new StringBuilder();
        for (int i = 0; i < twitter.length(); i++) {
            char c = twitter.charAt(i);
            if (Character.isWhitespace(c)) {
                if (currentWord.length() > 0) {
                    String word = currentWord.toString();
                    String upper = word.toUpperCase();
                    if (abbe.containsKey(upper)) {
                        sb.append(abbe.get(upper)).append(" ");
                    } else {
                        sb.append(word).append(" ");
                    }
                    currentWord.setLength(0);
                }
            } else {
                currentWord.append(c);
            }
        }
        if (currentWord.length() > 0) {
            String word = currentWord.toString();
            String upper = word.toUpperCase();
            if (abbe.containsKey(upper)) {
                sb.append(abbe.get(upper)).append(" ");
            } else {
                sb.append(word).append(" ");
            }
        }
        newSentence = sb.toString().trim();
        return newSentence;
    }
}
