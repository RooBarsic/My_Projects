import java.awt.print.Printable;
import java.io.PrintWriter;

public class MyLog {
    public static WordMemory wordMemory;
    public static MyMemory myMemory;

    public static void print(PrintWriter printWriter, String message){
        printWriter.print(message);
        printWriter.flush();
    }

    public static void println(PrintWriter printWriter, String message){
        printWriter.println(message);
        printWriter.flush();
    }

    public static void print(PrintWriter printWriter, int message){
        printWriter.print(message);
        printWriter.flush();
    }

    public static void println(PrintWriter printWriter, int message){
        printWriter.println(message);
        printWriter.flush();
    }
}
