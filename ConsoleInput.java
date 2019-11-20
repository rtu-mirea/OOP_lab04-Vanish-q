package com.company;

import java.util.Scanner;

public class ConsoleInput {
    private TextWork obj = new TextWork();
    public void consoleInputData(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку");
        obj.inputText(in.nextLine());
    }
    public TextWork getObject(){
        return obj;
    }
    public void setData(String str){
        obj.inputText(str);
    }
}
