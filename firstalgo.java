import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class firstalgo {
    public static HashMap<String, String> abbe = new HashMap<>();
    public static String abbev[][];
    public static void main(String[] args) {
        try {
            Scanner sc=new Scanner(System.in);
            String twitter=sc.nextLine();
            long startTime;
            long endTime;
            long finalt;
            dataset();
            long totaltimeh=0;
            long totaltimea=0;
            for(int i=0;i<10;i++){
            startTime=System.nanoTime();
            dataset();
            twitter(twitter);
            endTime=System.nanoTime();
            finalt=endTime-startTime;
            totaltimeh+=finalt;
            }
            System.out.println("Execute Time for hashmap: "+totaltimeh/10);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

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

    public static void twitter(String twitter) {
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
        System.out.println(newSentence);
    }

}
