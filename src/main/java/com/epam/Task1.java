package com.epam;

/**
 * Deadlock by two threads
 */
public class Task1 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Task 1");

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


        t1.join();
        t2.join();
    }

    public static void delay(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
        }
    }
}