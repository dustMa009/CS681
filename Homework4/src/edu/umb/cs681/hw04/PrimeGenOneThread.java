package edu.umb.cs681.hw04;

public class PrimeGenOneThread {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Generate all primes from 1 to 2000000 with one thread.");

        PrimeGenerator gen = new PrimeGenerator(1L, 2000000L);
        Thread t = new Thread(gen);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {}

        // gen.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );

        long primeNum = gen.getPrimes().size();
        System.out.println(primeNum + " prime numbers are found in total.");
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Time elapsed in milliseconds: " + timeElapsed / 1000000);
    }
}
