package com.company;

public class Dock {
    // не должен знать о существовании туннеля

    // нигде кроме порта использоваться не буден
    // может только разгружать корабли
    // будет на заморозке, пока не разгрузит корабль

    //для присваивания номера доку

    private static int dockCounter;

    //порядковы номер, который генерируется
     private  int dockNumber;

     // флаг о том что док пустой
    private boolean empty = true;


     //переопределим дефолтный конструктор
    public Dock(){
        this.dockNumber = ++ dockCounter;
    }
     public boolean isEmpty(){
        return this.empty;
     }

    //порт может засовывать корабль при помощи этого метода
    public  synchronized void unloadShip (Ship ship){ // добавить и разгрузить корабль
        this.empty =false;
        System.out.println(ship + "прибыл в док #" + this.dockNumber);
        //заморозить док на кол-во секунд, необходимы для разрузки корабля определенной вместимости

        try {
            Thread.sleep(ship.getWeight());//создали геттер в DOck
        } catch (InterruptedException e) {
            e.printStackTrace();  //оставляем по умолчанию
        }
        // после разгрузки
        //сообщаем что dock освободился

        //нам нужно условно синхронизирующий объект
        //жди пока не освободился

        this.notify();//сообщаем что мы свободны, notify нужно вызвать в синхронизирующем блоке--synchrinized в метод
        System.out.println("Док #" + this.dockNumber + "освободился");
        this.empty =true;



    }

}
