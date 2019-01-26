import jdk.nashorn.internal.ir.WhileNode;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class AlisaTranslator {

    public static void AlisaRun(Scanner scanner, PrintWriter printWriter){
        printlnAlisa(printWriter, "Hello. ");
        printlnAlisa(printWriter, "Now I will learn new words. ");
        printlnAlisa(printWriter, "I should read words from file or console ? ( file/console )");
        printAlisa(printWriter, "");
        String userAndswer = scanner.next();
        while(!(userAndswer.equals("file") || (userAndswer.equals("console")))){
            printlnAlisa(printWriter, "I can't understand you :(");
            printlnAlisa(printWriter, "Please tell me - I sould read words from file or console ? ( file / console )");
            printAlisa(printWriter, "");
        }
        Scanner wordsScanner;
        if(userAndswer.equals("console")) wordsScanner = new Scanner(System.in);
        else {
            printAlisa(printWriter, "write input file name : ");
            String inputFileName = scanner.next();
            try {
                wordsScanner = new Scanner(System.in);
                //wordsScanner = new Scanner(new File(inputFileName));
            } catch (Exception e) {
                e.printStackTrace();
                printlnAlisa(printWriter, "this file does not exist :(");
                printlnAlisa(printWriter, "file name = " + inputFileName);
                printlnAlisa(printWriter, "Bye user. :( ");
                return;
            }
        }
        printAlisa(printWriter, "number of words for translation = ");
        int n = scanner.nextInt();
        for(int i = 1; i <= n; i++) {
            printAlisa(printWriter, "next word : ");
            String word = scanner.next();
            if (word.equals("#exit")) {
                break;
            }
            String en = word;
            String ru = translateit(word);
            if (ru.equals("????")) {
                printlnAlisa(printWriter, " yandex translator does not work!!! ");
                printlnAlisa(printWriter, "Bye user :(");
                return;
            } else {
                printlnAlisa(printWriter, " en = " + en + " -> ru = " + ru + "          It's normal ?  ( 1 - yes, 2 - no )");
                printAlisa(printWriter, "");
                userAndswer = wordsScanner.next();
                while(!(userAndswer.equals("1") || userAndswer.equals("2") || userAndswer.equals("yes") || userAndswer.equals("no"))){
                    printlnAlisa(printWriter, "I can't understand your answer :( ");
                    printlnAlisa(printWriter, "Please it's normal ?  ( 1 - yes, 2 - no )");
                    printAlisa(printWriter, "");
                    userAndswer = wordsScanner.next();
                }
                if((userAndswer.equals("1")) ||(userAndswer.equals("yes"))){
                    Fucntions.pushNewWord(en, ru);
                    printlnAlisa(printWriter, " done ");
                }
                else printlnAlisa(printWriter, " not done ");
            }
        }

        printlnAlisa(printWriter, " So good bye user :)");
        printlnAlisa(printWriter, " Come again. ");
    }

    private static void printAlisa(PrintWriter printWriter, String message){
       MyLog.print(printWriter, " Alisa~~: " + message);
    }

    private static void printlnAlisa(PrintWriter printWriter, String message){
        MyLog.println(printWriter, " Alisa~~: " + message);
    }

    public static String translateit(String en){
        try {
            return translate("en-ru", en);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("  yandex translation eror!! ");
        }
        return "????";
    }

    private static String translate(String lang, String input) throws IOException {
        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20181230T200250Z.9595ea94b6fbcd4b.8c5e2a63c1d01bfba398850cee06b5d566060a1c";
        URL urlObj = new URL(urlStr);
        HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        String translated = json.substring(start + 2, end - 1);
        return translated;
    }
}
