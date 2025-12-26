package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileSaver {
    private final String fileName;

    public FileSaver(String fileName) {
        this.fileName = "save_" + fileName + ".txt";
    }

    public void save(int value){
        try{
            FileWriter fw = new FileWriter(fileName, false);
            fw.write(String.valueOf(value));
            fw.close();
        } catch(IOException e){
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }

    public int get(){
        try {
            File file = new File(fileName);
            if(!file.exists()){
                return 0;
            }

            Scanner sc = new Scanner(file);
            int savedValue = 0;
            if(sc.hasNextInt()){
                savedValue = sc.nextInt();
            }
            sc.close();
            return savedValue;
        } catch(IOException e){
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return 0;
    }
}
