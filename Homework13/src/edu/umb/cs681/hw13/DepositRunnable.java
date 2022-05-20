package edu.umb.cs681.hw13;

public class DepositRunnable implements Runnable{
    private boolean done = false;
    private BankAccount account;

    public DepositRunnable(BankAccount account) {
        this.account = account;
    }

    public void setDone() {
        done = true;
    }

    public void run(){
        while (true) {
            if (done) {
                break;
            }
            account.deposit(100);
        }
    }
}

