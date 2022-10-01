package com.company;

import java.util.LinkedList;

public class Tunnel {
    // не нужны динамические названия, потому что туннель один
    // задаем вместимость

    // создадим коллекцию в которой корабли в режиме ожидания в туннеле, на основе списка
    // часто добавление в конец и доставание из начала
    //LinkedList
    final LinkedList<Ship> shipList;//не будем прямо здесь выделять для него память, проинициализируем в конструкторе ниже Tunnel

    private int maxCapacity;

    public Tunnel (int maxCapacity){//вместимость туннеля
        this.shipList = new LinkedList<>();
        this.maxCapacity = maxCapacity;//если туннель полон

    }

    //добавляет корабль в туннель
    public void addShip (Ship ship) throws Exception {//метод добавления в туннель
        synchronized (this.shipList) {
            //проверим, поместиться ли корабль в тоннель
            if (this.shipList.size() >= this.maxCapacity)
                throw new Exception("tunnel is fill");//если Exception, то дальше код не выполняется
            System.out.println(ship + "прибыл в тоннель");
            this.shipList.add(ship);//добавляетя конец списка
        }
    }

    //метод будет отдавать корабль
    public Ship getShip() {
        synchronized (this.shipList) {

            //выпускаться будет первый по очереди корабль
            //если кораблей нет, то и возращать нечего
            if (this.shipList.isEmpty()) {
                throw new RuntimeException("tunnel is empty");
            }

            //вернуть корабль и удалить из списка ожидания
            return this.shipList.removeFirst(); //возвращает первый элемент из списка и его нам возвращает
        }
    }

    // возвращает true, пока есть свободное место в туннеле, faulse, если туннель забит
    public boolean isFull () {
        synchronized (this.shipList) {
            return this.shipList.size() >= this.maxCapacity;

        }
    }

}
