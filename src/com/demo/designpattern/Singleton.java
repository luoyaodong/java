package com.demo.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17-2-18.
 */

public class Singleton {
    private static Singleton instance;
    private Singleton(){
        System.out.println("the singleton instance has been created");
    }
    public static synchronized Singleton getInstance(){
        if(instance == null)
            instance = new Singleton();
        return instance;
    }
    public static void main(String[] args){
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1==s2);
        List<Integer> list = new ArrayList<Integer>();
    }
}
/*
 public class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton (){}
    public static Singleton getInstance() {
    return instance;
    }
}

public class Singleton {
    private volatile static Singleton singleton;
    private Singleton (){}
    public static Singleton getSingleton() {
    if (singleton == null) {
        synchronized (Singleton.class) {
        if (singleton == null) {
            singleton = new Singleton();
        }
        }
    }
    return singleton;
    }
}

public class Singleton {
    private static class SingletonHolder {
    private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton (){}
    public static final Singleton getInstance() {
    return SingletonHolder.INSTANCE;
    }
}
*/