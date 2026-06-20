package com.globalsoftwaresupport;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class LinkedBlockingQueueExample {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new PriorityBlockingQueue<>();

        Runnable producer = () -> {
            try {
                int i = 0;

                while(true) {
                    String s = "Item " + i++;
                    queue.put(s);
                    System.out.println("Produced: " + s);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            try {
                while(true) {
                    String s = queue.take();
                    System.out.println("Consumed: " + s);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
