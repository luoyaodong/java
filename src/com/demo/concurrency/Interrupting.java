package com.demo.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 17-2-22.
 */

    class SleepBlocked implements Runnable{
        public void run(){
            try {
                TimeUnit.SECONDS.sleep(100);
            }catch (InterruptedException e){
                System.out.println("Interupted Exception");
            }
            System.out.println("Exiting SleepBlocked.run()");
        }
    }
    class IOBlocked implements Runnable{
        private InputStream in;
        public IOBlocked(InputStream is){in = is;}
        public void run(){
            try{
                System.out.println("Waiting for read():");
                in.read();
            }catch (IOException e){
                if(Thread.currentThread().isInterrupted())
                System.out.println("Interrupted from blocked I/O");
                else {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Exciting IOBlocked.run()");
        }
    }
    class SynchronizedBlocked implements Runnable{
        public synchronized void f(){
            while(true){
                Thread.yield();
            }
        }
        public SynchronizedBlocked(){
            new Thread(){
                public void run(){
                    f();//never return
                }
            }.start();
        }
        public void run(){
            System.out.println("Trying to call f()");
            f();//so the f() has already been used so the program is blocked here
            System.out.println("Exciting SynchronizedBlocked.run()");
        }
    }

public class Interrupting{
    private static ExecutorService exec = Executors.newCachedThreadPool();
    static void test(Runnable r)throws InterruptedException{
        Future <?> f = exec.submit(r);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("Interrupting"+r.getClass().getName());
        f.cancel(true);
        System.out.println("Interrupting"+r.getClass().getName());
    }
    public static void main(String[] args){
        try {
            test(new SleepBlocked());
            test(new IOBlocked(System.in));
            test(new SynchronizedBlocked());
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Absorting with Syntem.exit(0)");
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
