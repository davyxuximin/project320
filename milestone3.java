import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
    public class milestone3 {
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
                arraydataset();
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
                for(int i=0;i<10;i++){
                startTime=System.nanoTime();
                arraydataset();
                arraytwitter(twitter);
                endTime=System.nanoTime();
                finalt=endTime-startTime;
                totaltimea+=finalt;
                }
                System.out.println("Execute Time for array: "+totaltimea/10);

            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }
    
        public static void dataset() throws FileNotFoundException {
            File file = new File("/Users/xuximin/Desktop/milestone3/dataset.txt");
            Scanner input = new Scanner(file);
    
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split("\t");
                abbe.put(parts[0], parts[1]);
            }
        }
        public static void twitter(String twitter){
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
            System.out.println(newSentence);
        }
        public static void arraydataset() throws FileNotFoundException {
            File file = new File("/Users/xuximin/Desktop/milestone3/dataset.txt");
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
        public static void arraytwitter(String twitter){
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
                System.out.println(newSentence);
              
                    
            }
           
        }