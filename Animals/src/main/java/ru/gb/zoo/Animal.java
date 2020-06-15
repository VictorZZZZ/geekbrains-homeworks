package ru.gb.zoo;

public class Animal {
    private String name;
    private int maxRunDistance;
    private int maxSwimDistance;
    private float maxJumpDistance;

    public Animal(String name, String color) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxJumpDistance() {
        return maxJumpDistance;
    }

    public void setMaxJumpDistance(float maxJumpDistance) {
        this.maxJumpDistance = maxJumpDistance;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }

    public int getMaxSwimDistance() {
        return maxSwimDistance;
    }

    public void setMaxSwimDistance(int maxSwimDistance) {
        this.maxSwimDistance = maxSwimDistance;
    }

    public void run(int distance){
        System.out.printf("%s runs on %d meters\n",this.getName(),distance);
    }

    public void swim(int distance){
        System.out.printf("%s swims on %d meters\n",this.getName(),distance);
    }

    public void jump(float height){
        System.out.printf("%s jumps on %.2f meters\n",this.getName(),height);
    }

}
