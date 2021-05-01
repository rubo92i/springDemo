package am.basic.springdemo.old;

class Waiter implements Runnable {

    private volatile boolean  shouldFinish;

    void  finish() {
        shouldFinish = true;
    }

    public void run() {
        long iteration = 0;
        while (!shouldFinish) {
            iteration++;
        }
        System.out.println("Finished after: " + iteration);
    }
}

class DataRace {

    public static void main(String[] args) throws InterruptedException {
        Waiter waiter = new Waiter();

        Thread waiterThread = new Thread(waiter);
        waiterThread.start();

        Thread.sleep(1000);

        waiter.finish();
        waiterThread.join();
    }
}
