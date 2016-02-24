package com.epam;

/**
 * Deadlock by two threads
 */
public class Task4 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Task 4");

        final Object resource1 = new Object();

        Thread t1 = new Thread("Slow thread") {
            @Override
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread 1 takes resource 1");
                    delay(200000);
                }
            }
        };

        Thread t2 = startThreadLockedBy(resource1, "Locked thread 1");
        Thread t3 = startThreadLockedBy(resource1, "Locked thread 2");
        Thread t4 = startThreadLockedBy(resource1, "Locked thread 3");
        Thread t5 = startThreadLockedBy(resource1, "Locked thread 4");

        t1.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }

    public static Thread startThreadLockedBy(final Object resource, final String name) {
        Thread t = new Thread(name) {
            @Override
            public void run() {
                delay(3000);
                System.out.println("Thread " + name + " attempts to take resource");
                synchronized (resource) {
                    System.out.println("Thread " + name + " took resource");
                }
            }
        };

        t.start();
        return t;
    }

    public static void delay(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
        }
    }
}