package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Runnabledirectory implements Runnable {

    private Directory root, a, b;
    private File x, y, z;

    public Runnabledirectory () {
        LocalDateTime time1 = LocalDateTime.of(2020, 1, 1,5, 5);
        LocalDateTime time2 = LocalDateTime.of(2021, 2, 1, 10, 10);
        root = new Directory(null, "root", 10, time1);
        a = new Directory(root, "a", 10, time1);
        b = new Directory(root, "b", 8, time1);
        x = new File(a, "x", 20, time2);
        y = new File(b, "y", 15, time2);
        z = new File(b, "z", 30, time2);
        root.appendChild(a);
        root.appendChild(b);
        a.appendChild(x);
        b.appendChild(y);
        b.appendChild(z);
    }

    @Override
    public void run() {
        System.out.println(a.getSize());
        System.out.println(b.getCreationTime());
        System.out.println(root.getSize());
        System.out.println(x.getName());
        System.out.println(y.getName());
        System.out.println(z.getName());

    }

    public static void main(String[] args) {
        Runnabledirectory gen = new Runnabledirectory();

        Thread[] t = new Thread[10];

        for (int i = 0; i < 10; i++) {
            t[i] = new Thread(gen);
        }

        for (int i = 0; i < 10; i++) {
            try {
                t[i].start();
                t[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}
