package com.company;

import java.util.ArrayList;

public class Toy {
    private String firm;
    private int price;
    private int lowAge;
    private int highAge;

    public Toy(){
        firm = "";
        price = -1;
        lowAge = -1;
        highAge = -1;
    }
    public Toy(String firm, int price, int lowAge, int highAge){
        this.firm = firm;
        this.price = price;
        this.lowAge = lowAge;
        this.highAge = highAge;
    }
    public String getFirm(){ return firm; }
    public void setFirm(String str){ firm = str; }
    public int getPrice(){return price;}
    public int getLowAge(){return lowAge;}
    public int getHighAge(){return highAge;}
    public int getPriceOfAge(int lowAge, int highAge){
        if (lowAge >= this.lowAge && highAge <= this.highAge)
            return this.price;
        else return -1;
    }
    public String getFirmOfAge(int lowAge, int highAge){
        if (lowAge >= this.lowAge && highAge <= this.highAge)
            return this.firm;
        else return "";
    }
    public boolean compare(Toy toy){
        return (lowAge == toy.lowAge && highAge == toy.highAge);
    }
    public static ArrayList<Toy> getOfFirm(ArrayList<Toy> arr, String firm){
        ArrayList<Toy> result = new ArrayList<Toy>();
        for(Toy toy: arr)
            if (toy.firm.compareTo(firm) == 0)
                result.add(toy);
        return result;
    }
    public static ArrayList<Toy> lessPrise(ArrayList<Toy> arr){
        for (Toy toy: arr)
            if(toy.highAge <= 12)
                toy.price/=2;
        return arr;
    }
}
