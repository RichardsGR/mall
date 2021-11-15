package com.gr.test;

public class Test {
    public static void main(String[] args) {
        char key = 'A';
        Po po = new Po();
        po.setValue(key);
        System.out.println(po.getValue());
        key = 'B';
        System.out.println(po.getValue());
    }
}
