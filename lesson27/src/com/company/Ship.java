package com.company;

import java.util.Random;

public class Ship {
    // 1,сначала создаем корабль
    // какой-то один корабль
    private String name; //уникальное имя корабля

    public int getWeight() {
        return weight;
    }




    private int weight;//вместимость для подсчета сколько времени обсуживается один корабль
    private static int shipNumber = 0; // cчетчик должен быть статическим, что бы каждый паз увеличивался, а не был равен 0
    private static Random rand = new Random();// что бы нагенерить NextInt

    // что бы имя и вместимоссть генерировалисть автоматом

    public Ship () {
        this.name = "ship" + ++shipNumber; // ++ что бы нумерация с 1, а не с 0
        // генерим диапазон по времени сколько будет обслуживаться
        // [1000, 10000]
        this.weight = rand.nextInt(9001)+1000;// если генериться 0, то +1000 =1000, если генериться 9000, то +1000 = 10000;


        // переопределяем метод toString
        // что бы выводился не хэшкод, а нормальный текст
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
