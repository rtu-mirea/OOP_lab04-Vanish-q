package com.company;

import java.io.Serializable;

public class TextWork implements Serializable {
    private String text;
    private int indOfPar = 0;

    public void inputText(String Text){text = Text;}

    public String getText(){return text;}

    public void paragraphSegmentation(){
        String buf = "";
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) != '.' && text.charAt(i) != '?' && text.charAt(i) != '!')
                buf += text.charAt(i);
            else{
                buf += text.charAt(i);
                buf+='\n';
                i++;
                indOfPar++;
            }
        }
        text = buf;
    }
    private String toUpperParagraph(String str){
        String buf = "";
        int start = 0, end;
        end = str.indexOf(' ');
        start = str.lastIndexOf(' ');
        buf += str.substring(0, end).toUpperCase()+ str.substring(end, start) + str.substring(start).toUpperCase();
        return buf;
    }
    public void toUpperWords(){
        String buf = "";
        int parArr[] = new int[indOfPar + 1];
        parArr[0] = 0;
        for (int i = 0, n = 1; i < text.length() && n < parArr.length; i++){
            if (text.charAt(i) == '\n'){
                parArr[n] = i;
                n++;
            }
        }
        for (int i = 0; i < parArr.length - 1; i++){
            buf+= toUpperParagraph(text.substring(parArr[i], parArr[i+1]));
        }
        text = buf + '\n';
    }
    public int findAmIn(int n) throws Exception{
        if (n > indOfPar) throw new Exception("Введенное число было больше количества параграфов!");
        String str = '\n' + text;
        int start = 0, end = 0, count = 0;
        for (int i = 0; i <= str.length();i++){
            if (count < n){
                start = str.indexOf('\n', i) + 1;
                i = start;
                end = str.indexOf('\n', i + 1) - 1;
                i = end;
                count++;
            }
            else break;
        }
        str = str.substring(start, end);
        count = 0;
        for (int i = 0; i < str.length(); i++){
            i = str.indexOf("ам", i) + 1;
            if (i > 0)
                count++;
            else return count;
        }
        return count;
    }
    public String wordNum(){
        Integer count = 1;
        String str = text;
        for(int i =0 ; i< text.length(); i++){
            if(text.charAt(i) == ' ' || text.charAt(i) == '\n') count++;
        }
        return str += "Количество слов в тексте = " + (--count);
    }
}
