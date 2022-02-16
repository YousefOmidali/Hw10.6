public class printThread {
    public synchronized void testMethod() {
        try {
            System.out.println("Doing some heavy processing for thread " +
                    Thread.currentThread().getName());
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        printThread td = new printThread();
        Thread t1 = new Thread(new MyRunnable(td), "t1");
        Thread t2 = new Thread(new AnotherRunnable(td), "t2");

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }
}

class MyRunnable implements Runnable {
    printThread td;

    MyRunnable(printThread td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.testMethod();
        td.testMethod();
        td.testMethod();
    }
}

class AnotherRunnable implements Runnable {
    printThread td;

    AnotherRunnable(printThread td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.testMethod();
    }
}