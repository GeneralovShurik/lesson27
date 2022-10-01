package com.company;

import java.util.LinkedList;

public class Port {

    private Tunnel tunnel;
    private Thread tunnelThread;
    private LinkedList<Dock> docks;//хранит объекты типа Dock (доки)

    // объединяет логику работы туннелей и доков

    //конструктор, принимающий на вход кол доков
    // только порт можен задвинуть новый корабль
    public Port (int dockCount, int tunnelCapacity){
        this.tunnel = new Tunnel(tunnelCapacity);
        this.docks = new LinkedList<>();
        for (int i = 0; i < dockCount; i++) {
            this.docks.add(new Dock());
        }
    }

    private void manageDocks(){
        for (Dock dock : this.docks){
            Thread t = new Thread(()-> {//создаем объект потока
                while (!Thread.currentThread().isInterrupted()){

                    try {
                        //ждем пока док освободиться
                        if(dock.isEmpty()){
                            try {
                            dock.unloadShip(this.tunnel.getShip());
                            } catch (RuntimeException e){
                              //  System.out.println("Док свободен, но в туннеле нет кораблей");
                            }
                            continue;//перейти к началу и не ждать dock
                        }
                        synchronized (dock){
                            dock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        t.start();
        }
    }

    private void manageTunnel(){
        //запускаем поток, внутри которого работаем с и=туннелеим
       this.tunnelThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){//возвращает ссылку на текущий поток объекта, работает пока не будет прерван

                //нужно получить информацию что туннель свободен
                if(!this.tunnel.isFull()){
                    //добавляем корабль в туннель
                    //нужно получить корабль из моря
                    Ship ship = Sea.createShip();
                    try {
                        this.tunnel.addShip(ship);
                    } catch (Exception e) {
                        e.printStackTrace();//выводит текст ошибки
                    }
                }

            }

        });
        //запускаем поток внутри метода
        this.tunnelThread.start();
    }

    public void start (){
        this.manageTunnel();
        this.manageDocks();//для каждого из доков запускаем отдельный поток
        try {
            this.tunnelThread.join();//блокирует выполнение основного потока, будет ждать выполнение метода
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
