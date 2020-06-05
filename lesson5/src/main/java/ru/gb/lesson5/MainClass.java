package ru.gb.lesson5;

public class MainClass {



    public static void main(String[] args) {
        //4. Создать массив из 5 сотрудников.
        Person[] personArray = new Person[]{
                new Person("Иванов Иван Иванович","ivanov@firma.com","директор","887-88-77",300000,52),
                new Person("Скворцов Сергей Иванович","skvorcov@firma.com","зам. директора","887-88-71",200000,44),
                new Person("Филимонова Анастасия Сергеевна","filimonova@firma.com","секретарь","887-88-70",100000,28),
                new Person("Васильев Степан Викторович","vasiliev@firma.com","водитель","887-52-70",150000,36),
                new Person("Красильников Аркадий Кузьмич","krasilnikov@firma.com","кладовщик","882-58-17",170000,41)
        };

        //5. *С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
        System.out.println("Все сотрудники старше 40 лет:");
        for (Person person : personArray) {
            if(person.getAge()>40) System.out.println(person);
        }

    }
}
