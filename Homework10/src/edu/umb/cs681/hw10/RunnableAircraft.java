package edu.umb.cs681.hw10;

public class RunnableAircraft extends Aircraft implements Runnable {
    private final Position nextPos;

    public RunnableAircraft(Position pos, Position nextPos) {
        super(pos);
        this.nextPos = nextPos;
    }

    @Override
    public void run() {
        System.out.println("Original Position: " + this.getPosition());
        this.setPosition(nextPos);
        System.out.println("New Position: " + this.getPosition());
    }

    public static void main(String[] args) {
        Position pos1 = new Position(15,30, 45);
        Position pos2 = new Position(12,13, 80);
        Position pos3 = new Position(67,68, 69);
        Position pos4 = new Position(30, 35, 40);
        RunnableAircraft run = new RunnableAircraft(pos1, pos2);
        RunnableAircraft run2 = new RunnableAircraft(pos1, pos3);
        RunnableAircraft run3 = new RunnableAircraft(pos3, pos4);
        Thread gen = new Thread(run);
        Thread gen2 = new Thread(run2);
        Thread gen3 = new Thread(run3);
        gen.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen2.start();
        gen3.start();
    }
}
