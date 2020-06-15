package ru.gb.zoo;

public class Cat extends Animal {

    public Cat(String name, String color) {
        super(name, color);
        this.setMaxRunDistance(200);
        this.setMaxJumpDistance(2.0F);
    }

    @Override
    public void run(int distance) {
        if(distance>this.getMaxRunDistance()){
            System.out.printf("Cat can not run more than %d meters. Try another distance.\n",this.getMaxRunDistance());
        } else {
            super.run(distance);
        }
    }


    @Override
    public void jump(float height) {
        if(height>this.getMaxJumpDistance()){
            System.out.printf("Cat can not jump more than %.2f meters. Try another distance.\n",this.getMaxJumpDistance());
        } else {
            super.jump(height);
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println("Cats coudn't swim.");
    }
    
}
