/*
Согласно Лицензии на использование Яндекс.Переводчика над или под результатом перевода должен быть указан текст Переведено сервисом «Яндекс.Переводчик» с активной ссылкой на страницу http://translate.yandex.ru/.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Согласно Лицензии на использование Яндекс.Переводчика над или под результатом перевода должен быть указан текст Переведено сервисом «Яндекс.Переводчик» с активной ссылкой на страницу http://translate.yandex.ru/.");
        preparing();
        mainStream();

        System.out.println("Bye");
    }



    public static void preparing(){
        System.out.println("Use default files or new ?");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        while(!((answer.equals("default")) || (answer.equals("new")))){
            System.out.println(" I can't understand! default or new ?");
            answer = scanner.next();
        }
        if(answer.equals("new")){
            System.out.print("translater_words_file_name : ");
            String wordsFileName = scanner.next();
            System.out.print("my_memory_words_file_name : ");
            String myWordsFileName = scanner.next();
            MyLog.myMemory = new MyMemory(myWordsFileName);
            MyLog.wordMemory = new WordMemory(wordsFileName);
        }
        else{
            MyLog.myMemory = new MyMemory("myWords.txt");
            MyLog.wordMemory = new WordMemory("wordsMemory.txt");
        }
    }

    public static void mainStream(){
        PrintWriter printWriter = new PrintWriter(System.out);
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag){
            System.out.println(" \n #exit #parse #new_from_file #new #compress #learn #stats #Alisa_learn");
            String command = scanner.next();

            switch (command){
                case "#stats" : {
                        MyLog.println(printWriter, " status ......... ");
                        MyLog.println(printWriter, " words = " + Integer.toString(MyLog.wordMemory.size()));
                        MyLog.println(printWriter, " know words = " + Integer.toString(MyLog.myMemory.konwSize()));
                        MyLog.println(printWriter, " dont know words = " + Integer.toString(MyLog.myMemory.dontKnowSize()));
                    }   break;
                case "#exit" : flag = false; break;
                case "#parse" : Fucntions.parseFile(scanner, printWriter); break;
                case "#new_from_file" : Fucntions.inputNewWordsFromFile(scanner, printWriter); break;
                case "#compress" : Fucntions.compress(scanner, printWriter); break;
                case "#learn" : Fucntions.learnWords(scanner, printWriter); break;
                case "#new" : {
                    MyLog.print(printWriter, " kol = ");
                    int kol = scanner.nextInt();
                    for(int i = 1; i <= kol; i++){
                        String newEn = scanner.next();
                        String newRu = scanner.next();
                        MyLog.wordMemory.addNewWord(newEn, newRu);
                    }
                    break;
                }
                case "#Alisa_learn" : Fucntions.AlisaLearn(scanner, printWriter); break;
                default: {
                    String translation = MyLog.wordMemory.getTranslation(command);
                    MyLog.println(printWriter," result : " + command + " <-> " + translation);
                }
            }
            MyLog.wordMemory.saveData();
            MyLog.myMemory.saveData();
        }
        MyLog.println(printWriter, " Saving words");
        MyLog.wordMemory.saveData();
        MyLog.myMemory.saveData();
        System.out.println(" Bye user. ");
    }
}
