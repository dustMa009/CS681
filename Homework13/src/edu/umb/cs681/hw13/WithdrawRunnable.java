package edu.umb.cs681.hw13;

public class WithdrawRunnable implements Runnable{
    private boolean done = false;
    private BankAccount account;

    public void setDone() {
        done = true;
    }

    public WithdrawRunnable(BankAccount account) {
        this.account = account;
    }

    public void run(){
        while (true) {
            if (done) {
                break;
            }
            account.withdraw(100);
        }
    }

}
