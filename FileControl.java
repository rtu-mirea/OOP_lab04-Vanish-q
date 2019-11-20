package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileControl {
    private String fileName = "";

    public void save(String name) throws Exception{
        Scanner in = new Scanner(System.in);
        Toy arr[];
        File file = new File(name);
        if (file.exists())
            throw new Exception("Файл с таким именем уже существует");
        else{
            file.createNewFile();
            fileName = name;
        }
        DataOutputStream output = new DataOutputStream(new FileOutputStream(file, true));
        System.out.print("Введите количество игрушек: ");
        arr = new Toy[in.nextInt()];
        for(int i = 0; i < arr.length; i++){
            System.out.println("Введите фирму производителя для игрушки №" + (i + 1));
            in.nextLine();
            name = in.nextLine();
            System.out.println("Введите подряд цену игрушки, минимальный возраст и максимальный возраст");
            arr[i] = new Toy(name, in.nextInt(), in.nextInt(), in.nextInt());
            output.writeUTF(arr[i].getFirm());
            output.writeInt(arr[i].getPrice());
            output.writeInt(arr[i].getLowAge());
            output.writeInt(arr[i].getHighAge());
        }
        output.close();
    }
    public ArrayList<Toy> load ()throws Exception{
        Scanner inC = new Scanner(System.in);
        ArrayList<Toy> toys = new ArrayList<Toy>();
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(fileName));
            while (true){
                toys.add(new Toy(in.readUTF(), in.readInt(), in.readInt(), in.readInt()));
            }
        }
        catch (Exception e){
            //System.err.println(e);

        }
        System.out.println("Введите название фирмы по которой будет производиться поиск");
        return Toy.getOfFirm(toys, inC.nextLine());

    }
    public ArrayList<Toy> randomAccess(ArrayList<Toy> arr)throws Exception{
            RandomAccessFile rf = new RandomAccessFile("FileForRandomAccess.txt", "rw");
            int buf = arr.size();
            for (Toy toy : arr) {
                if (toy.getFirm().length() < 10) {// до 10, потому что я посчитал что меньшее количество символов в названии фирмы будет не читабельно
                    String str = toy.getFirm();
                    for (int i = str.length(); i < 10; i++) {
                        str += " ";
                    }
                    toy.setFirm(str);
                }
                else{
                    toy.setFirm(toy.getFirm().substring(0, 10));
                }
                rf.writeUTF(toy.getFirm());
                rf.writeInt(toy.getPrice());
                rf.writeInt(toy.getLowAge());
                rf.writeInt(toy.getHighAge());
            }
            arr.clear();
            //System.out.println(rf.length());
            //String buf1;
            //int buf2, buf3, buf4;
            for (int i = 0; i < buf; i++){
                rf.seek(i*(3*4 + 10 + 2));//4*3 потому что у нас 3 перменные типа инт
                                               // + 10 потому что в строке по 10 символов и + 2, потому что строка в файле занимает на 2 символа больше, чем имеет символов
                /*
                System.out.println(rf.getFilePointer());
                buf1 = rf.readUTF();
                System.out.println(rf.getFilePointer());
                buf2 = rf.readInt();
                System.out.println(rf.getFilePointer());
                buf3 = rf.readInt();
                System.out.println(rf.getFilePointer());
                buf4 = rf.readInt();
                System.out.println(rf.getFilePointer());
                arr.add(new Toy(buf1, buf2, buf3, buf4));
                 */
                arr.add(new Toy(rf.readUTF(), rf.readInt(), rf.readInt(), rf.readInt()));
            }
            rf.close();
            return Toy.lessPrise(arr);
    }
}
