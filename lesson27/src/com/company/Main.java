package com.company;

public class Main {

    public static void main(String[] args) {

        // замудреная задача на потоки
        // sea
        // ship(w)
        // tunnel [3]---туннели
        // [1] [1] [1]---доки


        Port port = new Port(3,3);
        port.start();



    }
}
