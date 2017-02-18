package com.demo.luo;

/**
 * Created by root on 17-2-17.
 */
public class Parcel8 {
    public Wrapping wrapping(int x){
        return  new Wrapping(x){
            public int value(){
                return super.value() * 47;
            }
        };
    }
    public static void main(String args[]){
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(8);
    }
}
