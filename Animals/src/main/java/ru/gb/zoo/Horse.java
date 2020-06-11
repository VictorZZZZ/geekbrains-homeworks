package ru.gb.zoo;

public class Horse extends Animal {
    public Horse(String name, String color) {
        super(name, color);
        this.setMaxRunDistance(1500);
        this.setMaxSwimDistance(100);
        this.setMaxJumpDistance(3.0F);
    }

    @Override
    public void run(int distance) {
        if(distance>this.getMaxRunDistance()){
            System.out.printf("Horse can not run more than %d meters. Try another distance.\n",this.getMaxRunDistance());
        } else {
            super.run(distance);
        }
    }

    @Override
    public void swim(int distance) {
        if(distance>this.getMaxSwimDistance()){
            System.out.printf("Horse can not run more than %d meters. Try another distance.\n",this.getMaxSwimDistance());
        } else {
            super.swim(distance);
        }
    }

    @Override
    public void jump(float height) {
        if(height>this.getMaxJumpDistance()){
            System.out.printf("Horse can not jump more than %.2f meters. Try another distance.\n",this.getMaxJumpDistance());
        } else {
            super.jump(height);
        }
    }
}
