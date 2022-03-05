package edu.umb.cs681.hw04;

public class PrimeGenSixteenThread {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Generate all primes from 1 to 2000000 with sixteen threads.");

        PrimeGenerator gen1 = new PrimeGenerator(1L, 125000L);
        PrimeGenerator gen2 = new PrimeGenerator(125001L, 250000L);
        PrimeGenerator gen3 = new PrimeGenerator(250001L, 375000L);
        PrimeGenerator gen4 = new PrimeGenerator(375001L, 500000L);
        PrimeGenerator gen5 = new PrimeGenerator(500001L, 625000L);
        PrimeGenerator gen6 = new PrimeGenerator(625001L, 750000L);
        PrimeGenerator gen7 = new PrimeGenerator(750001L, 875000L);
        PrimeGenerator gen8 = new PrimeGenerator(875001L, 1000000L);
        PrimeGenerator gen9 = new PrimeGenerator(1000001L, 1125000L);
        PrimeGenerator gen10 = new PrimeGenerator(1125001L, 1250000L);
        PrimeGenerator gen11 = new PrimeGenerator(1250001L, 1375000L);
        PrimeGenerator gen12 = new PrimeGenerator(1375001L, 1500000L);
        PrimeGenerator gen13 = new PrimeGenerator(1500001L, 1625000L);
        PrimeGenerator gen14 = new PrimeGenerator(1625001L, 1750000L);
        PrimeGenerator gen15 = new PrimeGenerator(1750001L, 1875000L);
        PrimeGenerator gen16 = new PrimeGenerator(1875001L, 2000000L);

        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        Thread t3 = new Thread(gen3);
        Thread t4 = new Thread(gen4);
        Thread t5 = new Thread(gen5);
        Thread t6 = new Thread(gen6);
        Thread t7 = new Thread(gen7);
        Thread t8 = new Thread(gen8);
        Thread t9 = new Thread(gen1);
        Thread t10 = new Thread(gen2);
        Thread t11 = new Thread(gen3);
        Thread t12 = new Thread(gen4);
        Thread t13 = new Thread(gen5);
        Thread t14 = new Thread(gen6);
        Thread t15 = new Thread(gen7);
        Thread t16 = new Thread(gen8);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t16.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();
            t11.join();
            t12.join();
            t13.join();
            t14.join();
            t15.join();
            t16.join();
        } catch (InterruptedException e) {}

        // gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
        // gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );

        long primeNum = gen1.getPrimes().size() + gen2.getPrimes().size() + gen3.getPrimes().size() + gen4.getPrimes().size();
        primeNum = primeNum + gen5.getPrimes().size() + gen6.getPrimes().size() + gen7.getPrimes().size() + gen8.getPrimes().size();
        primeNum = primeNum + gen9.getPrimes().size() + gen10.getPrimes().size() + gen11.getPrimes().size() + gen12.getPrimes().size();
        primeNum = primeNum + gen13.getPrimes().size() + gen14.getPrimes().size() + gen15.getPrimes().size() + gen16.getPrimes().size();
        System.out.println(primeNum + " prime numbers are found in total.");
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Time elapsed in milliseconds: " + timeElapsed / 1000000);
    }
}
