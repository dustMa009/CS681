package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimeFactors() {
        long divisor = 2;
        while( dividend != 1 && divisor <= to ){
            lock.lock();
            try {
                if (done) {
                    System.out.println("Stopped factorizing.");
                    this.factors.clear();
                    break;
                }

                if(dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                }else {
                    if (divisor == 2) {
                        divisor++;
                    } else {
                        divisor += 2;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(2873, 2, 2873);
        Thread thread = new Thread(gen);
        thread.start();
        // gen.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
