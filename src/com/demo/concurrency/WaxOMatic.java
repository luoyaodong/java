package com.demo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 17-2-22.
 */
class Car{
    private boolean waxOn = false;
    public synchronized void waxed(){
        waxOn = true;
        notifyAll();
    }
    public synchronized void buffed(){
        waxOn = false;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException{
        while(waxOn == false)
            wait();
    }
    public synchronized void waitForBuffing() throws InterruptedException{
        while (waxOn == true)
            wait();
    }
}
class  WanOn implements Runnable{
    private Car car;
    public WanOn(Car c){car = c;}
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                System.out.print("Wax On!");
                TimeUnit.MILLISECONDS.sleep(2000);
                car.waxed();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            System.out.println("Exciting via interrupt");
        }
        System.out.println("Ending Wax On tast");
    }
}
class WaxOff implements Runnable{
    private Car car;
    public WaxOff(Car c){car = c;}
    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                car.waitForWaxing();
                System.out.print("Wax Off!");
                TimeUnit.MILLISECONDS.sleep(2000);
                car.buffed();
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
        }
        System.out.println("Ending Wax Off task");
    }
}
public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WanOn(car));
        TimeUnit.SECONDS.sleep(20);
        exec.shutdownNow();
    }
}
