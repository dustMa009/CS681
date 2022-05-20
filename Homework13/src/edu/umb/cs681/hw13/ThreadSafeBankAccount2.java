package edu.umb.cs681.hw13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 implements BankAccount{
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();

    Condition sufficientFundsCondition = lock.newCondition();
    Condition belowUpperLimitFundsCondition = lock.newCondition();

    public void withdraw(double amount){
        lock.lock();
        while (balance <= 0) {
            // Wait for the balance to exceed 0
            try {
                sufficientFundsCondition.await();
            } catch (InterruptedException e) {
                return;
            }
        }
        balance -= amount;
        System.out.println("withdraw " + this.balance);
        belowUpperLimitFundsCondition.signalAll();
        lock.unlock();
    }

    public void deposit(double amount) {
        lock.lock();
        while (balance >= 300) {
            // Wait for the balance to go below 300.
            try {
                belowUpperLimitFundsCondition.await();
            } catch (InterruptedException e) {
                return;
            }
        }
        balance += amount;
        System.out.println("deposit " + this.balance);
        sufficientFundsCondition.signalAll();
        lock.unlock();
    }

    public static void main(String[] args){
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        DepositRunnable gen1 = new DepositRunnable(bankAccount);
        WithdrawRunnable gen2 = new WithdrawRunnable(bankAccount);
        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        t1.start();
        t2.start();
        gen1.setDone();
        gen2.setDone();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
