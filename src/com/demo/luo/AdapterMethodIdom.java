package com.demo.luo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by root on 17-2-17.
 */
class ReversibleArrayList<T> extends ArrayList<T>{
    public ReversibleArrayList(Collection<T> c){super(c);}
    public Iterable<T> reversed(){
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int current = size() - 1;
                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public T next() {
                        return get(current--);
                    }
                    public void remove(){
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}
public class AdapterMethodIdom {
    public static void main(String[] args){
        ReversibleArrayList<String> ral = new ReversibleArrayList<String>(Arrays.asList("To be or not to be".split(" ")));
        //Grabs the ordinary iterator via iterator():
        for(String s:ral)
            System.out.print(s+" ");
        System.out.println();
        for(String s:ral.reversed())
            System.out.print(s+" ");
    }
}
