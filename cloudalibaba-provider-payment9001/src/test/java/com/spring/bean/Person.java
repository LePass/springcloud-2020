package com.spring.bean;

/**
 * @author Wangx
 * @create 2020/7/7
 * @since 1.0.0
 */
public class Person<T> {

    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Person{" +
                "obj=" + obj +
                '}';
    }
}
