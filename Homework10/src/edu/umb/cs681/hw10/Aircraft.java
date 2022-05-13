package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class Aircraft {
    private Position position;
    private final ReentrantLock lock = new ReentrantLock();

    public Aircraft(Position pos) {
        this.position = pos;
    }

    public void setPosition(Position pos) {
        lock.lock();
        try {
            this.position = pos;
        } finally {
            lock.unlock();
        }
    }

    public Position getPosition() {
        lock.lock();
        try {
            return this.position;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {

    }
}
