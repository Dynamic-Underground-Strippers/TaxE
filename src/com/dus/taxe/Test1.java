package com.dus.taxe;


public class Test1 {

    Map map = new Map();
    Node a = map.retrieveNode(1);
    String b = a.toString();


    public static void main(String[] args) {
        System.out.println("yolo");
        Map map = new Map();
        Node a = map.retrieveNode(1);
        String b = a.toString();
        System.out.println(b);
        a = map.retrieveNode(2);
        b = a.toString();
        System.out.println(b);
        a = map.retrieveNode(3);
        b = a.toString();
        System.out.println(b);



    }

    public void a() {
        System.out.print(b);
    }

}
