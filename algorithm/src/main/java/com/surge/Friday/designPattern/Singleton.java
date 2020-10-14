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
}
//  private static class SingletonHolder {
//    private static final Singleton INSTANCE = new Singleton();
//    }
//    private Singleton (){}
//    public static final Singleton getInstance() {
//    return SingletonHolder.INSTANCE;
//    }