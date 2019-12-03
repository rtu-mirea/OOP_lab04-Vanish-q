package com.company;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class ClassTextFile {
    private String fileName;

    public ClassTextFile(){
        fileName = "";
    }
    public ClassTextFile(String name) throws Exception{
        if(new File(name).exists())
            fileName = name;
        else throw new Exception("Файл с данным именем не существует");
    }
    public TextWork task4(){
        try {
            Scanner in = new Scanner(new DataInputStream(new FileInputStream(new File(fileName))));
            TextWork obj = new TextWork();
            obj.inputText(in.nextLine());
            return obj;
        }
        catch (Exception e){}
        return new TextWork();
    }
}
