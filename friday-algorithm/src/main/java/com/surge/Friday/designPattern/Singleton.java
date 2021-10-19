package com.surge.Friday.designPattern;

/**
 * <p>单例模式
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.08.05
 */
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

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        double a = 5.5;
        double b = 41.27;
        double c = 7.42;

        System.out.println(a + b + c);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }

}

