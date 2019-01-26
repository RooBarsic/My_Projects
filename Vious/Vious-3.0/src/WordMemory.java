import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class WordMemory {
    private HashMap<String, String > words;
    private String fileName;

    WordMemory(String fileName){
        if((fileName == null) || (fileName.equals(""))) fileName = "wordsMemory.txt";
        System.out.println(" WordsMemory - file : " + fileName);
        this.fileName = fileName;
        words = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(fileName))){
            String en, ru;
            while(scanner.hasNext()){
                en = scanner.nextLine();
                ru = scanner.nextLine();
                words.put(en, ru);
                MyLog.myMemory.addNewWord(en);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getTranslation(String en){
        String ru = words.get(en);
        if(ru != null) return ru;
        System.out.println(" I don't now word = " + en);
        return null;
    }

    public int size() {
        return words.size();
    }

    public void addNewWord(String en, String ru){
        words.put(en, ru);
    }

    public void saveData(){
        try (PrintWriter printWriter = new PrintWriter(new File(fileName))){
            words.forEach(
                    (a, b)->{
                        MyLog.println(printWriter, a + "\n" + b);
                    });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
