package ru.gb;

import java.io.*;
import java.util.Scanner;

public class FilesLesson {

    public static void main(String[] args) {
        createTxtFile("testDir\\f1.txt","turpis massa sed elementum tempus egestas sed sed risus \n pretium quam vulputate dignissim suspendisse in est ante in nibh mauris cursus mattis molestie a \\n iaculis at erat pellentesque adipiscing commodo elit at imperdiet dui accumsan sit amet nulla facilisi morbi \n tempus iaculis urna id volutpat lacus laoreet non curabitur gravida");
        createTxtFile("testDir\\f2.txt","porttitor eget dolor morbi non arcu risus quis varius quam \n quisque id diam vel quam elementum pulvinar etiam non quam lacus suspendisse \n faucibus interdum posuere lorem ipsum dolor sit amet consectetur adipiscing elit duis tristique sollicitudin \n nibh sit amet commodo nulla facilisi nullam vehicula ipsum a arcu cursus vitae congue");
        stickFiles("testDir\\f1.txt","testDir\\f2.txt","testDir\\target.txt");
        System.out.println(findWordInFile("testDir\\f1.txt","vulputate"));
        System.out.println(findWordInDir("testDir","vulputate"));
        appendWordToAllFiles("testDir"," Geekbrains");
    }

    //5. *** Написать метод, добавляющий, указанное слово в файлы в папке
    private static void appendWordToAllFiles(String path,String word){
        byte[] byteWord = word.getBytes();
        File dir = new File(path);
        if(dir.isDirectory()){
            for(File f:dir.listFiles()){
                if(f.isFile()){
                    try {
                        OutputStream os = new FileOutputStream(f,true);
                        os.write(byteWord,0,byteWord.length);
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(f.isDirectory()) appendWordToAllFiles(f.getPath(),word);
            }
        }
    }

    //4. ** Написать метод, проверяющий, есть ли указанное слово в файлах в папке
    private static boolean findWordInDir(String path,String searchWord){
        File dir = new File(path);
        if(dir.isDirectory()){
            for(File f:dir.listFiles()){
                if(f.isFile() && findWordInFile(f.getPath(),searchWord)){
                    return true;
                }
                //вызов себя рекурсивно
                if(f.isDirectory() && findWordInDir(f.getPath(),searchWord)){
                    return true;
                }
            };
        }
        return false;
    }

    //3. * Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
    private static boolean findWordInFile(String filename, String searchWord){
        try {
            InputStream is = new FileInputStream(filename);
            Scanner sc = new Scanner(is);
            while(sc.hasNext()){
                String s = sc.next();
                if(s.contains(searchWord)) {
                    is.close();
                    return true;
                }
            }
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

//    2. Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго в новый файл.
    private static void stickFiles(String f1,String f2,String target) {
        try {
            InputStream isF1 = new FileInputStream(f1);
            InputStream isF2 = new FileInputStream(f2);
            OutputStream os = new FileOutputStream(target,false);
            int b;
            while ((b = isF1.read())!=-1){
                os.write(b);
            }
            while ((b = isF2.read())!=-1){
                os.write(b);
            }
            isF1.close();
            isF2.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //1. Создать 2 текстовых файла, примерно по 50-100 символов в каждом (особого значения не имеет);
    private static void createTxtFile(String filePath,String content) {
        try {
            PrintStream ps =new PrintStream( new FileOutputStream(filePath, false));
            ps.print(content);
            ps.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

