package edu.umb.cs681.hw14;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {

    private File[] files;
    private AccessCounter counter;
    private ReentrantLock lock = new ReentrantLock();
    private AtomicBoolean done = new AtomicBoolean(false);

    public RequestHandler() {
        File dir = new File("test");
        files = dir.listFiles();
        counter = AccessCounter.getInstance();
    }

    public void setDone() {
        lock.lock();
        try {
            done.set(true);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (done.get()) {
                    System.out.println("Thread stopped.");
                    break;
                }

                Random rand = new Random();
                File file = files[rand.nextInt(files.length)];
                counter.increment(file.toPath());
                System.out.println(file + " count: " + counter.getCount(file.toPath()));

            } finally {
                lock.unlock();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }

    public static void main(String[] args) {
        Thread[] t = new Thread[10];

        RequestHandler gen = new RequestHandler();

        for (int i = 0; i < 10; i++) {
            t[i] = new Thread(gen);
            t[i].start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                t[i].interrupt();
                gen.setDone();
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}