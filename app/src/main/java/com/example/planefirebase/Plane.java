package com.example.planefirebase;

public class Plane {
    public String id,name,maxspeed,passenger; //поля класса Plane

    //1. Создаем пустой конструктор (Правая кнопка мыши по области - Generate - Select None)
    public Plane() {
    }
    //2. Создаем заполненный конструктор, те поля, которые хотим добавить в базу данных
    //(Правая кнопка мыши по области - Generate - Выделяем все созданные поля в  public class Plane)
    public Plane(String id, String name, String maxSpeed, String passenger) {
        this.id = id;
        this.name = name;
        this.maxspeed = maxSpeed;
        this.passenger = passenger;
    }
}
