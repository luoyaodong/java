package com.demo.luo;

/**
 * Created by root on 17-2-17.
 */
public class Rudolph {
    public static void main(String[] args){
        for(String pattern:new String[]{"Rudolph","[rR]udolph","[rR][aeiou][a-z]ol.*","R.*"}){
            System.out.println("Rudolph".matches(pattern));
        }
    }
}
