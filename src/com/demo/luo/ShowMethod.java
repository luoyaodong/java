package com.demo.luo;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by root on 17-2-18.
 */
public class ShowMethod {
    private static String usage = "usage:\n" +
            "Showmethods qualified.class.name\n" +
            "To show all methods in class or:\n" +
            "ShowMethods qulified.class.name word\n" +
            "To serch for methods involving 'word'";
    private static Pattern p = Pattern.compile("\\w+\\.");
    public static void main(String[] args){
        if(args.length<1){
            System.out.println(usage);

            System.exit(0);
        }
        int lines = 0;
        try{
            System.out.println(args.length);
            Class<?> c = Class.forName(args[0]);
            Method[] method = c.getMethods();
            Constructor[] constructors = c.getConstructors();
            if(args.length == 1){
                for(Method m:method)
                    System.out.println(p.matcher(m.toString()).replaceAll(""));
                for(Constructor ctor:constructors)
                    System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                lines = method.length+constructors.length;
            }else {
                for(Method m:method)
                    if(m.toString().indexOf(args[1])!=-1){
                        System.out.println(p.matcher(m.toString()).replaceAll(""));
                        lines++;
                    }
                for(Constructor ctor:constructors)
                    if(ctor.toString().indexOf(args[1])!=-1){
                        System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                        lines++;
                    }
            }
        }catch (Exception e){
            System.out.println("No such class:"+e);
        }
    }
}
