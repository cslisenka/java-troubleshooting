package com.epam;

/**
 * Deadlock by two threads
 */
public class Task3 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Task 3");

        final Object resource1 = new Object();
        final Object resource2 = new Object();
        final Object resource3 = new Object();
        final Object resource4 = new Object();

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

                    System.out.println("Thread 2 attempts to take resource 3");
                    synchronized (resource3) {
                        System.out.println("Thread 2 takes resource 3");
                    }
                }
            }
        };

        Thread t3 = new Thread("My thread 3") {
            @Override
            public void run() {
                synchronized (resource3) {
                    System.out.println("Thread 3 takes resource 3");
                    delay(2000);

                    System.out.println("Thread 3 attempts to take resource 4");
                    synchronized (resource4) {
                        System.out.println("Thread 3 takes resource 4");
                    }
                }
            }
        };

        Thread t4 = new Thread("My thread 4") {
            @Override
            public void run() {
                synchronized (resource4) {
                    System.out.println("Thread 4 takes resource 4");
                    delay(2000);

                    System.out.println("Thread 4 attempts to take resource 1");
                    synchronized (resource1) {
                        System.out.println("Thread 4 takes resource 1");
                    }
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }

    public static void delay(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
        }
    }
}