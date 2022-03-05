package edu.umb.cs681.hw04;

public class PrimeGenTwoThread {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Generate all primes from 1 to 2000000 with two threads.");

        PrimeGenerator gen1 = new PrimeGenerator(1L, 1000000L);
        PrimeGenerator gen2 = new PrimeGenerator(1000001L, 2000000L);
        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}

        // gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
        // gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );

        long primeNum = gen1.getPrimes().size() + gen2.getPrimes().size();
        System.out.println(primeNum + " prime numbers are found in total.");
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Time elapsed in milliseconds: " + timeElapsed / 1000000);
    }
}
