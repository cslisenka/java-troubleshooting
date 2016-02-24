package com.epam;

/**
 * Deadlock by two threads
 */
public class Task2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Task 2");

        final Object resource1 = new Object();
        final Object resource2 = new Object();

        Thread t1 = new Thread("My thread 1") {
            @Override
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread 1 takes resource 1");
                    delay(2000);

                    System.out.println("Thread 1 attempts to take resource 2");
                    synchronized (resource2) {
                        System.out.println("Thread 2 takes resource 2");
                    }
                }
            }
        };

        Thread t2 = new Thread("My thread 2") {
            @Override
            public void run() {
                synchronized (resource2) {
                    System.out.println("Thread 2 takes resource 2");
                    delay(2000);

                    System.out.println("Thread 2 attempts to take resource 1");
                    synchronized (resource1) {
                        System.out.println("Thread 2 takes resource 1");
                    }
                }
            }
        };

        t1.start();
        t2.start();

        Thread t11 = startThreadLockedBy(resource1, "My thread 1 resource1");
        Thread t12 = startThreadLockedBy(resource1, "My thread 2 resource1");
        Thread t13 = startThreadLockedBy(resource1, "My thread 3 resource1");

        Thread t21 = startThreadLockedBy(resource2, "My thread 1 resource2");
        Thread t22 = startThreadLockedBy(resource2, "My thread 2 resource2");
        Thread t23 = startThreadLockedBy(resource2, "My thread 3 resource2");

        t1.join();
        t2.join();
        t11.join();
        t12.join();
        t13.join();
        t21.join();
        t22.join();
        t23.join();
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