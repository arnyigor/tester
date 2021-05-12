package com.arny.tester.tests;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadTest implements Testable {

    @Override
    public void runTest(@Nullable String[] args) {
        startThreads();
    }

    void startThreads() {
        AtomicInteger a = new AtomicInteger();
        AtomicInteger b = new AtomicInteger();
        Thread thread = new Thread(() -> {
            System.out.println("thread:" + Thread.currentThread().getName() + " a:" + a + " b:" + b);
        });
        Thread thread1 = new Thread(() -> {
            a.set(1);
            b.set(2);
            System.out.println("thread1:" + Thread.currentThread().getName() + " a:" + a.get() + " b:" + b + "\n\n");
        });
        thread.start();
        thread1.start();
    }
}
