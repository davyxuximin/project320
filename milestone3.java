import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
    public class milestone3 {
        public static HashMap<String, String> abbe = new HashMap<>();
        public static String abbev[][];
        public static void main(String[] args) {
            try {

                long startTime;
                long endTime;
                long finalt;
                for (int i = 0; i < 100; i++) {
                    String dummyInput = generateLargeInput(1000); // Smaller input for warm-up
                    arraydataset();
                    twitter(dummyInput);
                    abbe = new HashMap<>();
                    dataset();
                    arraytwitter(dummyInput);
                }
                dataset();
                arraydataset();
                String largeInput = generateLargeInput(10000); 
                long totaltimeh=0;
                long totaltimea=0;
                for(int i=0;i<50;i++){
                startTime=System.nanoTime();
                twitter(largeInput);
                endTime=System.nanoTime();
                finalt=endTime-startTime;
                totaltimeh+=finalt;
                }
                System.out.println("Execute Time for hashmap: "+totaltimeh/50);
                for(int i=0;i<50;i++){
                startTime=System.nanoTime();
                arraytwitter(largeInput);
                endTime=System.nanoTime();
                finalt=endTime-startTime;
                totaltimea+=finalt;
                }
                System.out.println("Execute Time for array: "+totaltimea/50);

            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }
    
        public static void dataset() throws FileNotFoundException {
            File file = new File("/Users/xuximin/Desktop/320/dataset.txt");
            Scanner input = new Scanner(file);
    
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split("\t");
                abbe.put(parts[0], parts[1]);
            }
        }
        public static String twitter(String twitter){
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
        public static void arraydataset() throws FileNotFoundException {
            File file = new File("/Users/xuximin/Desktop/320/dataset.txt");
            Scanner input = new Scanner(file);
            int countword=0;
            int countline=0;
            while (input.hasNextLine()) {
                countline++;
                input.nextLine();
            }
            input.close();

            abbev = new String[countline][2];
    
            input = new Scanner(file);
            countline=0;
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split("\t");
                abbev[countline][0]=parts[0];
                abbev[countline][1]=parts[1];
                countword=0;
                countline+=1;
            }
        }
        public static String arraytwitter(String twitter){
            twitter = twitter.replaceAll("[\\pP‘'“”]", "");
            StringBuilder sb = new StringBuilder();
            String twit[]=twitter.split(" ");
            String newSentence="";
            boolean found=false;
            int line=0;
            for(String str:twit){
                String upper=str.toUpperCase();
                for(int i=0;i<abbev.length;i++){
                    if(upper.equals(abbev[i][0])){
                        found=true;
                        line=i;
                        break;
                    }

                }
                if(found){
                    sb.append(abbev[line][1]).append(" ");
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
        }