package edu.umb.cs681.hw07;

public class RunnableConcurrentSingleton implements Runnable {

    public RunnableConcurrentSingleton() { }

    @Override
    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
    }

    public static void main(String[] args) {
        RunnableConcurrentSingleton gen = new RunnableConcurrentSingleton();
        RunnableConcurrentSingleton gen2 = new RunnableConcurrentSingleton();
        Thread thread1 = new Thread(gen);
        Thread thread2 = new Thread(gen2);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
