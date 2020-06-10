package ru.gb.lesson5;

public class Person {
//    1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.

    private String fio;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    //2. Конструктор класса должен заполнять эти поля при создании объекта.
    public Person(String fio, String position, String email, String phone, int salary, int age) {
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
    @Override
    public String toString() {
        return  fio +" " + position + " " + email +" тел.:" + phone +" з/п:" + salary + " возраст:" + age ;
    }
}
