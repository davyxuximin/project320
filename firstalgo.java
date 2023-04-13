import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;


public class firstalgo {
    
    // This HashMap stores key-value pairs from a dataset file.
    public static HashMap<String, String> abbe = new HashMap<>();
    
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String twitter = sc.nextLine();
            firstdataset();
            System.out.println(firstalgo(twitter));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
    
    // This method reads key-value pairs from a file and stores them in a HashMap.
    public static void firstdataset() throws FileNotFoundException {
        File file = new File("/Users/xuximin/Desktop/320/dataset.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] parts = line.split("\t");
            abbe.put(parts[0], parts[1]);
        }
    }
    
    // This method processes an input string by looking up each word in the HashMap and replacing it with its value.
    public static String firstalgo(String twitter){
        twitter = twitter.replaceAll("[\\pP‘'“”]", "");
        StringBuilder sb = new StringBuilder();
        String twit[]=twitter.split(" ");
        String newSentence="";
        for(String str:twit){
            String upper=str.toUpperCase();
            if(abbe.containsKey(upper)){
                sb.append(abbe.get(upper)).append(" ");
            }else{
                sb.append(str).append(" ");
            }
            newSentence = sb.toString().trim();
        }
        return newSentence;
    }
}
