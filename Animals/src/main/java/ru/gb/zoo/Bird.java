package ru.gb.zoo;

public class Bird extends Animal {
    public Bird(String name, String color) {
        super(name, color);
        this.setMaxRunDistance(5);
        this.setMaxJumpDistance(0.1F);
    }

    @Override
    public void run(int distance) {
        if(distance>this.getMaxRunDistance()){
            System.out.printf("Bird can not run more than %d meters. Try another distance.\n",this.getMaxRunDistance());
        } else {
            super.run(distance);
        }
    }
    

    @Override
    public void jump(float height) {
        if(height>this.getMaxJumpDistance()){
            System.out.printf("Bird can not jump more than %.2f meters. Try another distance.\n",this.getMaxJumpDistance());
        } else {
            super.jump(height);
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println("Birds coudn't swim.");
    }
}
