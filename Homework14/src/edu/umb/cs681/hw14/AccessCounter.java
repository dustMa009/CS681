package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private ConcurrentHashMap<Path, AtomicInteger> counter = new ConcurrentHashMap<>();
    private static AccessCounter instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private AccessCounter() {}

    public static AccessCounter getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public void increment(Path path) {
        counter.compute(path, (k, v) -> {
            v = v == null? new AtomicInteger(1) : new AtomicInteger(v.incrementAndGet());
            System.out.println("Thread " + Thread.currentThread().getName() + " " + k + " count: " + v);
            return v;
        });
    }

    public int getCount(Path path) {
        return counter.compute(path, (k, v) -> {
            AtomicInteger count = v == null? new AtomicInteger(0): v;
            System.out.println("Thread " + Thread.currentThread().getName() + " " + k + " count: " + v);
            return count;
        }).intValue();
    }
}
