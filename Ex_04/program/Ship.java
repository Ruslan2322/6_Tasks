package Lesson_06.Ex_04;


import java.util.Random;

public class Ship {
    Random random = new Random();
    public int capacity;
    public boolean bool = false;
    int pirs;

    public int getPirs() {
        return pirs;
    }

    public void setPirs(int pirs) {
        this.pirs = pirs;
    }

    public Ship() {
        this.bool = random.nextBoolean();
        if (this.bool) {
            capacity = 10;
        } else {
            capacity = 0;
        }
    }
}