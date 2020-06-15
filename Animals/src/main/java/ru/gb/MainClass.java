package ru.gb;

import ru.gb.zoo.*;

public class MainClass {
    public static void main(String[] args) {
        Animal[] zoo = new Animal[]{
                new Cat("Vaska","white"),
                new Dog("Bim","black"),
                new Dog("Jack","black"),
                new Horse("Princess","brown"),
                new Bird("Chick","grey"),
                new Dog("Matilda","brown-black",600)
        };

        int catsCount=0;
        int dogsCount=0;

        for(Animal animal:zoo){
            animal.run(590);
            animal.swim(20);
            animal.jump(2);
            System.out.println("-------");
            animal.run(4);
            animal.swim(5);
            animal.jump(8);
            System.out.println("========");

            //* Добавить подсчет созданных котов, собак и животных.
            if(animal instanceof Dog){
                dogsCount++;
            }
            if(animal instanceof Cat){
                catsCount++;
            }
        }
        System.out.println();
        System.out.println();


        System.out.println("Dogs count="+dogsCount);
        System.out.println("Cats count="+catsCount);
        System.out.println("Total Animals count="+zoo.length);
    }
}
