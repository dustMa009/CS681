package edu.umb.cs681.hw11;

import java.io.File;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
    private File[] files;
    private AccessCounter counter;
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RequestHandler() {
        File dir = new File("test");
        files = dir.listFiles();
        counter = AccessCounter.getInstance();
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (done) {
                    System.out.println("Thread stopped.");
                    this.counter.clear();
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

        for (int i = 0; i < 10; i++) {
            RequestHandler gen = new RequestHandler();
            t[i] = new Thread(gen);
            t[i].start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gen.setDone();
            t[i].interrupt();
        }

        for (int i = 0; i < 10; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
