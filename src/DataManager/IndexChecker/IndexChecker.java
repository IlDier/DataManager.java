package DataManager.IndexChecker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class IndexChecker {
    public static int Index ;
    private static File file = new File("D:/JavaProjects/ApProjectSpring2023/src/DataManager/IndexChecker/Index.txt");
    public static void indexCall(){
        getIndex();
        setIndex();
    }
    private static void getIndex(){
        try {
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();
            Index = Integer.parseInt(line);
            Index %= 3;
            reader.close();
        } catch (Exception o) {}
    }
    private static void setIndex(){
        try {
            FileWriter fw = new FileWriter(file,false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(++Index);
            bw.close();
            fw.close();
        } catch (Exception o ){}
    }
}
