package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private static HashMap<Path, Integer> counter;
    private static AccessCounter instance = null;
    private ReentrantLock lock = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private AccessCounter() {
        counter = new HashMap<Path, Integer>();
    }

    public static AccessCounter getInstance() {
        lock2.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
            return instance;
        } finally {
            lock2.unlock();
        }
    }

    public void increment(Path path) {
        lock.lock();
        try {
            if (counter.containsKey(path)) {
                counter.put(path, counter.get(path) + 1);
            } else {
                counter.put(path, 1);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getCount(Path path) {
        lock.lock();
        try {
            if (counter.containsKey(path)) {
                return counter.get(path);
            } else {
                return 0;
            }
        } finally {
             lock.unlock();
        }
    }

    public void clear() {
        lock.lock();
        try {
            this.counter.clear();
        } finally {
            lock.unlock();
        }
    }
}
