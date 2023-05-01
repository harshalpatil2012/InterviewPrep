package com.practice.desPattern;

/**
 * Prof Bill Pugh, University of Maryland, was the main force behind java memory model changes. His principle
 * Initialization-on-demand holder idiom also uses static block but in different way. It suggest to use static inner
 * class.
 */
class SingletonStatic {
    // HashMap<K, V>

    /**
     * Returns Singleton instance
     * 
     * @return Singleton
     */
    public static SingletonStatic getInstance() {
        return InstanceHolder.INSTANCE;
    }

    // private constructor prevents instantiation from other classes
    private SingletonStatic() {

    }

    // Maintains single instance of class
    private static class InstanceHolder {
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }

}

/*
 * A journey to write double checked locking of Singleton class in Java.
 */

class Singleton {

    private static volatile  Singleton _instance;

    private Singleton() {
        // preventing Singleton object instantiation from outside
    }

    /*
     * 1st version: creates multiple instance if two thread access this method simultaneously
     */

    public static Singleton getInstance() {
        if (_instance == null) {
            _instance = new Singleton();
        }
        return _instance;
    }

    /*
     * 2nd version : this definitely thread-safe and only creates one instance of Singleton on concurrent environment
     * but unnecessarily expensive due to cost of synchronization at every call.
     */

    public static synchronized Singleton getInstanceTS() {
        if (_instance == null) {
            _instance = new Singleton();
        }
        return _instance;
    }

    /*
     * 3rd version : An implementation of double checked locking of Singleton. Intention is to minimize cost of
     * synchronization and improve performance, by only locking critical section of code, the code which creates
     * instance of Singleton class. By the way this is still broken, if we don't make _instance volatile, as another
     * thread can see a half initialized instance of Singleton.
     */

    public static Singleton getInstanceDC() {
        if (_instance == null) {
            synchronized (Singleton.class) {
                if (_instance == null) {
                    _instance = new Singleton();
                }
            }
        }
        return _instance;
    }
}
