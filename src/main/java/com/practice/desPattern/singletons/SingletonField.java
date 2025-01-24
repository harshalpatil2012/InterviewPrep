package com.practice.desPattern.singletons;

/**
 * Singleton with public static final field.
 * Not safe with deserialization.
 *
 */
public class SingletonField {

    public static final SingletonField INSTANCE = new SingletonField();

    private SingletonField() {
    }

}