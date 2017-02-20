package com.demo.luo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by root on 17-2-18.
 */
interface Interface{
    void dosomething();
    void dosomethingelse(String arg);
}
class RealObject implements Interface{
    public void dosomething(){
        System.out.println("dosomething");
    }
    public void dosomethingelse(String arg){
        System.out.println("somethingelse:"+arg);
    }
}
class DynamiProxyHandler implements InvocationHandler{
    private Object proxied;
    public DynamiProxyHandler(Object proxied){
        this.proxied = proxied;
    }
    public Object invoke(Object proxy, Method method, Object[] args)throws Throwable{
        System.out.println("**** proxy:"+proxy.getClass()+",method:"+method+",args:"+args);
        if(args!=null)
            for(Object arg:args)
                System.out.println("   "+arg);
        return method.invoke(proxied,args);
    }
}
class SimpleDynamicProxy{
    public static void consumer(Interface iface){
        iface.dosomething();
        iface.dosomethingelse("banobo");
    }
    public static void main(String[] args){
        RealObject real = new RealObject();
        consumer(real);
        //Insert a proxy and call again
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),new Class[]{Interface.class},
                new DynamiProxyHandler(real));
        consumer(proxy);
    }
}
