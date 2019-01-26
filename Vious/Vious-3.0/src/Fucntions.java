import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class Fucntions {

    public static boolean checkWord(char w){
        if(('a' <= w) && (w <= 'z')) return true;
        if(('A' <= w) && (w <= 'Z')) return true;
        if(w == '\'') return true;
        return false;
    }

    /**
     * Use to parse english words in file.
     * User will write input file name file1 and output file name is file1_rest.txt
     * @param scanner
     * @param printWriter
     */
    public static void parseFile(Scanner scanner, PrintWriter printWriter){
        MyLog.print(printWriter, "file name : ");
        String fileName = scanner.next();

        try(Scanner fscanner = new Scanner(new File(fileName))){
            MyLog.println(printWriter, "    begin..... ");
            PrintWriter fprinter = new PrintWriter(new File(fileName + "_rest.txt"));
            ArrayList<String > arr = new ArrayList<>();
            while(fscanner.hasNext()) {
                String s = fscanner.nextLine();
                String t = "";
                for (int i = 0; i < s.length(); i++) {
                    if (checkWord(s.charAt(i))) {
                        t += s.charAt(i);
                    } else {
                        if (t.length() > 0) arr.add(t);
                        t = "";
                    }
                }
                if(t.length() > 0) arr.add(t);
                t = "";
            }
            MyLog.println(fprinter, arr.size());
            arr.forEach((s)->{
                MyLog.println(fprinter, s);
            });
            MyLog.print(printWriter, "   end ...... ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Use for input new words from file
     * In file should be translation of words
     * @param scanner
     * @param printWriter
     */
    public static void inputNewWordsFromFile(Scanner scanner, PrintWriter printWriter){
        MyLog.println(printWriter, " start ....... ");
        MyLog.print(printWriter, "file name : ");
        String fileName = scanner.next();

        try(Scanner fscanner = new Scanner(new File(fileName))){
            int k = fscanner.nextInt();
            ArrayList<String> enWords = new ArrayList();
            String st = fscanner.nextLine();
            for(int i = 1; i <= k; i++){
                enWords.add(fscanner.nextLine());
            }
            System.out.println(" last en = " + enWords.get(enWords.size() - 1));

            ArrayList<String > ruWords = new ArrayList();
            for(int i = 1; i <= k; i++){
                ruWords.add(fscanner.nextLine());
            }
            System.out.println(" last ru = " + ruWords.get(ruWords.size() - 1));

            for(int i = 0; i < k; i++){
                MyLog.myMemory.addNewWord(enWords.get(i));
                MyLog.wordMemory.addNewWord(enWords.get(i), ruWords.get(i));
            }
            MyLog.println(printWriter, "  done ...... ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Use to compress number of equal words in file to one
     * @param scanner
     * @param printWriter
     */
    public static void compress(Scanner scanner, PrintWriter printWriter){
        MyLog.print(printWriter, "file name : ");
        String fileName = scanner.next();

        try(Scanner fscanner = new Scanner(new File(fileName))){
            MyLog.println(printWriter, "    begin..... ");
            int k = fscanner.nextInt();
            LinkedHashSet<String > set = new LinkedHashSet<>();
            while(fscanner.hasNext()) {
                String s = fscanner.next();
                //s = siftDouwn(s);
                set.add(s);
            }
            PrintWriter fprinter = new PrintWriter(new File(fileName + "_compres.txt"));
            MyLog.println(fprinter, set.size());
            set.forEach((s)->{
                MyLog.println(fprinter, s);
            });
            MyLog.print(printWriter, "   end ...... ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static String siftDouwn(String s){
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < sb.length(); i++){
            if(('A' <= sb.charAt(i)) && (sb.charAt(i) <= 'Z')) sb.setCharAt(i, (char)((int)sb.charAt(i) - (int)'A' + (int)'a'));
        }
        return sb.toString();
    }

    /**
     * Learning programe main
     * @param scanner
     * @param printWriter
     */
    public static void learnWords(Scanner scanner, PrintWriter printWriter){

        MyLog.println(printWriter, " start learning ");
        MyLog.println(printWriter, " 1 - I know ");
        MyLog.println(printWriter, " 2 - I don't know ");
        MyLog.println(printWriter, " 3 - stop this part");
        MyLog.println(printWriter, " Start from words you dont know ");
        while (true){
            String word = MyLog.myMemory.getDontKnowWord();
            String translation = MyLog.wordMemory.getTranslation(word);
            MyLog.print(printWriter, " learning: ~ " + word + " <-> ");
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MyLog.print(printWriter, translation + "         ");
            //MyLog.print(printWriter, " learning: ~ ");

            int stat = scanner.nextInt();
            if(stat == 3) break;
            else if(stat == 1){
                MyLog.myMemory.changeWordStatus(word);
            }
            MyLog.println(printWriter, "");
        }


        MyLog.println(printWriter, " Now words you know and should repeat ");
        while (true){
            String word = MyLog.myMemory.getKnowWord();
            String translation = MyLog.wordMemory.getTranslation(word);
            MyLog.println(printWriter, " learning: ~ " + word + " <-> " + translation);
            MyLog.print(printWriter, " learning: ~ ");

            int stat = scanner.nextInt();
            if(stat == 3) break;
            else if(stat == 2){
                MyLog.myMemory.changeWordStatus(word);
            }
        }

        MyLog.myMemory.saveData();

        MyLog.println(printWriter, " finish learning ");
    }

    /**
     * use
     */
    public static void AlisaLearn(Scanner scanner, PrintWriter printWriter){
        AlisaTranslator.AlisaRun(scanner, printWriter);
    }
    public static void pushNewWord(String en, String ru){
        MyLog.wordMemory.addNewWord(en, ru);
        MyLog.myMemory.addNewWord(en);
    }
}
