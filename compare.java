import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
    public class compare {

        public static HashMap<String, String> abbe = new HashMap<>();
        public static String abbev[][];
        public static void main(String[] args) {
            try {
                long startTime;
                long endTime;
                long finalt;
                long totaltimeh = 0;
                long totaltimesec=0;
                // Loop to warm up the JVM by processing a smaller input string with both algorithms.
                for (int i = 0; i < 1000; i++) {
                    String dummyInput = generateLargeInput(1000); // Smaller input for warm-up
                    firstdataset();
                    firstalgo(dummyInput);
                    abbe = new HashMap<>();
                    seconddataset();
                    secondalgo(dummyInput);
                }
                String largeInput = generateLargeInput(10000); 
                // Loop to time the execution of the first algorithm with a large input string.
                for (int i = 0; i < 50; i++) {
                    startTime = System.nanoTime();
                    firstalgo(largeInput);
                    endTime = System.nanoTime();
                    finalt = endTime - startTime;
                    totaltimeh += finalt;
                }
                System.out.println("Execute Time for first algo: " + (totaltimeh / 50)  + " ms");
            // Loop to time the execution of the second algorithm with a large input string.
                for (int i = 0; i < 50; i++) {
                    startTime = System.nanoTime();
                    secondalgo(largeInput);
                    endTime = System.nanoTime();
                    finalt = endTime - startTime;
                    totaltimesec += finalt;
                }
                System.out.println("Execute Time for second algo: " + (totaltimesec / 50)  + " ms");
                
               
               
    
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }
    
        public static void firstdataset() throws FileNotFoundException {
            File file = new File("/Users/xuximin/Desktop/320/dataset.txt");
            Scanner input = new Scanner(file);
    
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split("\t");
                abbe.put(parts[0], parts[1]);
            }
        }
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
        
        
        public static String generateLargeInput(int n) {
            Random random = new Random();
            StringBuilder input = new StringBuilder();
    
            for (int i = 0; i < n; i++) {
                if (i > 0 && i % 10 == 0) {
                    input.append(' '); 
                } else {
                    input.append((char) (random.nextInt(26) + 'a'));
                }
            }
    
            return input.toString();
        }
        public static void seconddataset() throws FileNotFoundException {
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
    
        public static String secondalgo(String twitter) {
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
    