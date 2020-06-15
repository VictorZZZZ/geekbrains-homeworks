package ru.gb.zoo;

public class Dog extends Animal{
    public Dog(String name, String color) {
        super(name, color);
        this.setMaxRunDistance(500);
        this.setMaxSwimDistance(10);
        this.setMaxJumpDistance(0.4F);
    }

//  Добавить животным разброс в ограничениях. То есть у одной собаки может быть ограничение на бег 400 м., у другой 600 м..
    public Dog(String name, String color,int maxRunDistance) {
        super(name, color);
        this.setMaxRunDistance(maxRunDistance);
        this.setMaxSwimDistance(10);
        this.setMaxJumpDistance(0.4F);
    }

    @Override
    public void run(int distance) {
        if(distance>this.getMaxRunDistance()){
            System.out.printf("Dog can not run more than %d meters. Try another distance.\n",this.getMaxRunDistance());
        } else {
            super.run(distance);
        }
    }

    @Override
    public void swim(int distance) {
        if(distance>this.getMaxSwimDistance()){
            System.out.printf("Dog can not run more than %d meters. Try another distance.\n",this.getMaxSwimDistance());
        } else {
            super.swim(distance);
        }
    }

    @Override
    public void jump(float height) {
        if(height>this.getMaxJumpDistance()){
            System.out.printf("Dog can not jump more than %.2f meters. Try another distance.\n",this.getMaxJumpDistance());
        } else {
            super.jump(height);
        }
    }
}
