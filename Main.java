package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        FileControl obj = new FileControl();
        ArrayList<Toy> toys;
        int task = 8;
        while (task != 0) {
            try {
                System.out.print("[0] - Завершение работы" + '\n' +
                        "[1] - Задание 1 (Разобраться с файлами)" + '\n' +
                        "[2] - Задание 2 (применить знания на практике)" + '\n' +
                        "[3] - Задание 3 (Разобраться с текстовыми файлами)" + '\n' +
                        "[4] - Задание 4 (Сделать что-то)" + '\n' +
                        "Выберите нужное задание: ");
                task = in.nextInt();
                switch (task) {
                    case 0:
                        System.out.println("Завершение работы");
                        break;
                    case 1:
                        Task1();
                        break;
                    case 2:
                        in.nextLine();
                        System.out.println("Введите имя файла");
                        obj.save(in.nextLine());
                        toys = obj.load();
                        for (Toy toy : toys) {
                            System.out.println("Фирма игрушки: " + toy.getFirm() + ", " +
                                    "цена за игрушку: " + toy.getPrice() + ", " +
                                    "Возрастное ограничение: " + toy.getLowAge() + '-' + toy.getHighAge());
                        }
                        System.out.println("Работа с RandomAccess");
                        toys = obj.randomAccess(toys);
                        for (Toy toy : toys) {
                            System.out.println("Фирма игрушки: " + toy.getFirm() + ", " +
                                    "цена за игрушку: " + toy.getPrice() + ", " +
                                    "Возрастное ограничение: " + toy.getLowAge() + '-' + toy.getHighAge());
                        }
                        break;
                    case 3:
                        task3Ex1();
                        task3Ex2();
                        task3Ex3();
                        break;
                    case 4:
                        task4();
                        break;
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    private static void Task1(){
        Scanner in  = new Scanner(System.in);
        try {
            //Упражнение 1
            System.out.println("Упражнение 1 ---------------------------------------------------------------");
            File F1 = new File("MyFile1.txt");
            F1.createNewFile();
            System.out.println("Файл №1:");
            if (F1.exists())
                System.out.println(F1.getAbsolutePath());
            else
                System.out.println("File not exist");

            File F2 = new File("/MyFile2.txt");
            F2.createNewFile();
            System.out.println("Файл №2:");
            if (F2.exists())
                System.out.println(F2.getAbsolutePath());
            else
                System.out.println("File not exist");

            //System.out.print("Введите путь к файлу №3: D:\\");
            File F3 = new File("D:\\Vanish\\MyFile3.txt");
            F3.createNewFile();
            System.out.println("Файл №3:");
            if (F3.exists())
                System.out.println(F3.getAbsolutePath());
            else
                System.out.println("File not exist");

            File dir = new File("first\\second\\third");
            System.out.println("Папка третьего уровня");
            if (!dir.exists())
                if (dir.mkdirs())
                    System.out.println(dir.getAbsolutePath());
                else
                    System.out.println("не была создана");
            else
                System.out.println(dir.getAbsolutePath());

            //Упражнение 2
            System.out.println("\nУпражнение 2 ---------------------------------------------------------------");
            if (F3.isFile()){
                System.out.println("Вызывающий объект является файлом" + '\n' +
                        "Его имя: " + F3.getName() + '\n' +
                        "Его родительская папка: " + F3.getParent());
            }
            else System.out.println("Вызывающий объект не является файлом");
            if (dir.isDirectory()){
                System.out.println("Вызывающий объект является папкой\n" +
                        "Имя данной папки: " + dir.getName());
            }
            else System.out.println("Вызывающий объект не является папкой");

            File F4 = new File("MyFile1.txt");
            if (F4.exists())
                System.out.println("Файл " + F4.getName() + " существует в папке приложения");
            else
                System.out.println("Файл MyFile1.txt не существует в папке проекта");
            System.out.println("Полный путь файла " + F1.getName() + ": " + F1.getAbsolutePath());

            if (F1.isFile())
                System.out.println("Размер файла " + F1.getName() + " в байтах равен " + F1.length());
            else if (F1.isDirectory())
                System.out.println("Размер папки " + F1.getName() + " в байтах равен " + F1.length());
            else System.out.println("Данный оъект не является ни папкой, ни файлом");

            //Упражнение 3
            System.out.println("\nУпражнение 3 ---------------------------------------------------------------");
            File dir1 = new File("Papka");
            dir1.mkdir();

            File dir2 = new File("D:\\Vanish\\ООП Java\\Laba4");
            String arr[] = dir2.list();
            System.out.println("Файлы из папки из массива строк:");
            for (String str: arr){
                System.out.println(str);
            }
            System.out.println();
            int count = 0;
            File arr2[] = dir2.listFiles();
            System.out.println("Файлы из папки из массива файлов:");
            for(File file: arr2){
                System.out.println(file.getAbsolutePath());
                if(file.isFile()) count++;
            }
            System.out.println("Файлов в папке приложения было " + count);

            F1.delete();
            F2.delete();
            F3.delete();
            dir.delete();
            dir = new File(dir.getParent());
            dir.delete();
            dir = new File(dir.getParent());
            dir.delete();
            dir1.delete();
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    private static void task3Ex1(){
        try {
            Reader reader = new InputStreamReader(new FileInputStream("T1.txt"));
            Writer writer = new OutputStreamWriter(new FileOutputStream("T2.txt"));
            int x;
            while((x = reader.read()) != -1) {
                writer.write((char)x);
            }
            System.out.println("Все символы были пернесены из файла T1.txt в файл T2.txt");
            reader.close();
            writer.close();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
    private static void task3Ex2(){
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream("A.txt"));
            for (int i = 40; i < 552; i++) {
                writer.write((char) i);
            }
            writer.close();
            System.out.println("Файл A.txt был заполнен 512 символами");

            BufferedReader inb = new BufferedReader(new InputStreamReader(new FileInputStream("A.txt")), 128);
            BufferedWriter outb = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("B.txt")), 128);
            char[] buf = new char[128];
            for(int i = 0; i < 4; i++){
                inb.read(buf);
                outb.write(buf);
                outb.newLine();
            }
            System.out.println("Символы по 128 штук были перенесены из файла A.txt в B.txt");
            inb.close();
            outb.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    private static void task3Ex3(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Vanish\\ООП Java\\Laba4\\src\\A.txt"), "Cp1251"));
            System.out.println("Кодировка системы: " + Charset.defaultCharset().name());
            String str = in.readLine();
            System.out.println("Строка прочитанная в неверной кодировке:\n" + str);
            in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Vanish\\ООП Java\\Laba4\\src\\B.txt"), "UTF-8"));
            str = in.readLine();
            System.out.println("Строка прочитанная в верной кодировке:\n" + str);
        }
        catch (Exception e){System.err.println(e);}
    }
    private static void task4(){
        int task = 666;
        Scanner in = new Scanner(System.in);
        while(task != 0){
            try{
                System.out.print("[0] - Возвращение к предыдущему меню" + '\n' +
                        "[1] - проверка класса получающего данные из файла" +'\n' +
                        "[2] - проверка класса консольного ввода" +'\n' +
                        "[3] - проверка класса сериализации" +'\n' +
                        "Выбериет нужный варант: ");
                task = in.nextInt();
                switch (task){
                    case 0:
                        System.out.println("Возвращение к предыдущему меню");
                        break;
                    case 1:
                        in.nextLine();
                        System.out.println("Введите название файла");
                        ClassTextFile obj = new ClassTextFile(in.nextLine());
                        TextWork textObj = obj.task4();
                        System.out.println("екст объекта, прочитанный из файла\n" + textObj.getText());
                        break;
                    case 2:
                        ConsoleInput object = new ConsoleInput();
                        object.consoleInputData();
                        System.out.println(object.getObject().getText());
                        break;
                    case 3:
                        in.nextLine();
                        System.out.println("Введите название файла для сериализации");
                        SerilisationClass o = new SerilisationClass(in.nextLine());
                        System.out.println("Файл был создан");
                        o.makeCollection();
                        System.out.println("Коллекция была создана");
                        o.readCollection();
                        System.out.println("Коллекция была десериализирована");
                        ArrayList<TextWork> arr = o.getObjectCollection();
                        for (TextWork i:
                             arr) {
                            System.out.println(i.getText());
                        }
                        break;
                }
            }
            catch (Exception e){System.err.println(e);}
        }
    }
}
