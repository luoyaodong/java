package com.demo.luo;
import java.io.*;
import java.io.File;
import java.util.Random;

/**
 * Created by root on 17-2-17.
 */
public class Hex {
    public static String format(byte[] data){
        StringBuilder result = new StringBuilder();
        int n = 0;
        for(byte b :data){
            if(n%16==0)
                result.append(String.format("%05X:",n));
            result.append(String.format("%02X",b));
            n++;
            if(n%16==0) result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }
    public static void main(String[] args){
        char a = 'a';
        int b = Integer.valueOf(a);
        StringBuilder s = new StringBuilder();
        Random r = new Random(40);
        for (int i = 0;i<10;i++){
            System.out.println(r.nextInt(100));
            s.append(String.format("%05X ",39));
        }

        s.append(Integer.toBinaryString(b));
        System.out.println("s:"+s);

    }
}
