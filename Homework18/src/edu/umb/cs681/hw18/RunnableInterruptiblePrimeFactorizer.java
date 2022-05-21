package edu.umb.cs681.hw18;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunnableInterruptiblePrimeFactorizer  extends PrimeFactorizer implements Runnable {
    public RunnableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend);
        if (from >= 2 && to >= from) {
            this.from = from;
            this.to = to;
        } else {
            throw new RuntimeException(
                    "from must be >= 2, and to must be >= from." + "from==" + from + " to==" + to);
        }
    }

    protected boolean isEven(long n){
        if(n%2 == 0){ return true; }
        else{ return false; }
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while( dividend != 1 && divisor <= to ){
            if( divisor > 2 && isEven(divisor)) {
                divisor++;
                continue;
            }
            if(dividend % divisor == 0) {
                factors.add(divisor);
                dividend /= divisor;
            }else {
                if(divisor==2){ divisor++; }
                else{ divisor += 2; }
            }
        }
    }

    public void run() {
        generatePrimeFactors();
        System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
    }

    public static void main(String[] args) {
        long number = 2489;
        RunnableInterruptiblePrimeFactorizer gen1 = new RunnableInterruptiblePrimeFactorizer(number, 2, (long)Math.sqrt(number) );
        RunnableInterruptiblePrimeFactorizer gen2 = new RunnableInterruptiblePrimeFactorizer(number, 1+(long)Math.sqrt(number), number / 2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(gen1);
        executor.execute(gen2);
        executor.shutdown();
//		executor.shutdownNow();
        try {
            executor.awaitTermination(600, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}