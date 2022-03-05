package edu.umb.cs681.hw04;

public class PrimeGenFourThread {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Generate all primes from 1 to 2000000 with four threads.");

        PrimeGenerator gen1 = new PrimeGenerator(1L, 500000L);
        PrimeGenerator gen2 = new PrimeGenerator(500001L, 1000000L);
        PrimeGenerator gen3 = new PrimeGenerator(1000001L, 1500000L);
        PrimeGenerator gen4 = new PrimeGenerator(1500001L, 2000000L);

        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        Thread t3 = new Thread(gen3);
        Thread t4 = new Thread(gen4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {}

        // gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
        // gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );

        long primeNum = gen1.getPrimes().size() + gen2.getPrimes().size() + gen3.getPrimes().size() + gen4.getPrimes().size();
        System.out.println(primeNum + " prime numbers are found in total.");
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Time elapsed in milliseconds: " + timeElapsed / 1000000);
    }
}
