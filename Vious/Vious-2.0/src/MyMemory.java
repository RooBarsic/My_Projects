import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class MyMemory {
    private LinkedList< String > know;
    private HashMap< String, String > allWords;
    private LinkedList< String > dontKnow;
    private String fileName;

    /*
    word status(yes/no) number
     */
    MyMemory(String fileName){
        know = new LinkedList<>();
        dontKnow = new LinkedList<>();
        allWords = new HashMap<>();

        System.out.println(" file = " + fileName);
        if((fileName == null) || (fileName.equals(""))) fileName = "myWords.txt";
        this.fileName = fileName;
        try (Scanner scanner = new Scanner(new File(fileName))){
            // ---- know --------
            int k = scanner.nextInt();
            String word = scanner.nextLine();
            for(int i = 1; i <= k; i++) {
                word = scanner.nextLine();
                if (allWords.get(word) == null) {
                    know.addLast(word);
                    allWords.put(word, "yes");
                }
            }

            // ---- dont know -----------
            int m = scanner.nextInt();
            word = scanner.nextLine();
            for(int i = 1; i <= m; i++){
                word = scanner.nextLine();
                if (allWords.get(word) == null) {
                    dontKnow.addLast(word);
                    allWords.put(word, "no");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(" fileName = " + fileName);
        }
        catch (Exception e){
            System.out.println("exceptino  fileName = " + fileName);
        }
    }

    public String getKnowWord(){
        if(know.size() == 0) {
            System.out.println(" known words is empty! ");
            return "empty :(";
        }
        know.addLast(know.getFirst());
        know.removeFirst();
        return know.getLast();
    }

    public String getDontKnowWord() {
        if (dontKnow.size() == 0) {
            System.out.println(" dontKnown words is empty! ");
            return "empty :(";
        }
        dontKnow.addLast(dontKnow.getFirst());
        dontKnow.removeFirst();
        return dontKnow.getLast();
    }

    public void changeWordStatus(String word){
        String status = allWords.get(word);
        if(status == null) return;
        if(status.equals("yes")){
            know.remove(word);
            dontKnow.addFirst(word);
            allWords.put(word, "no");
        }
        else if(status.equals("no")){
            dontKnow.remove(word);
            know.addLast(word);
            allWords.put(word, "yes");
        }
    }

    public void addNewWord(String word){
        String status = allWords.get(word);
        if(status == null){
            allWords.put(word, "no");
            dontKnow.addFirst(word);
        }
    }

    public int konwSize(){
        return know.size();
    }

    public int dontKnowSize(){
        return dontKnow.size();
    }

    public void saveData(){
        try (PrintWriter printWriter = new PrintWriter(new File(fileName))){
            MyLog.println(printWriter, know.size());
            know.forEach(
                    (a)->{
                        MyLog.println(printWriter, a);
                    });
            MyLog.println(printWriter, dontKnow.size());
            dontKnow.forEach(
                    (a)->{
                        MyLog.println(printWriter, a);
                    });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
