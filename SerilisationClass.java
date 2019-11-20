package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SerilisationClass {
    private String fileName = "";
    private ArrayList<TextWork> arr = new ArrayList<TextWork>();

    public SerilisationClass(String fileName) throws Exception{
        File f = new File(fileName);
        f.createNewFile();
        this.fileName = fileName;
    }
    public void serialisation(TextWork obj) throws Exception{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(obj);
        out.close();
    }
    public TextWork readObject()throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        TextWork obj = (TextWork)in.readObject();
        in.close();
        return obj;
    }
    public ArrayList<TextWork> getObjectCollection(){return arr; }
    public void makeCollection()throws Exception{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        ArrayList<TextWork> arr = new ArrayList<TextWork>();
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество объектов, которое хотите создать");
        int x = in.nextInt();
        in.nextLine();
        for (int i = 0; i < x; i++){
            arr.add(new TextWork());
            System.out.println("Введите текст для объекта №" + (i + 1));
            arr.get(i).inputText(in.nextLine());
            out.writeObject(arr.get(i));
        }
        out.close();
        //this.arr = arr;
    }
    public void readCollection()throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        ArrayList<TextWork> arr = new ArrayList<TextWork>();
        try{
            while(true){
                arr.add((TextWork)in.readObject());
            }
        }
        catch (Exception e){}
        this.arr = arr;
    }

}
