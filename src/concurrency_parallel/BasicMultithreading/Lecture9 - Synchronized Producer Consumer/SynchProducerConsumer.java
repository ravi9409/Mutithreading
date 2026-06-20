package com.globalsoftwaresupport;

import java.util.LinkedList;
import java.util.List;

class SharedBuffer {

    private List<Integer> buffer = new LinkedList<>();
    private int capacity = 5;

    public synchronized void produce() throws InterruptedException {
        // Joshau Bloch's approach - spuriously (without a real notify).
        while(buffer.size() == capacity) {
            System.out.println("Buffer full, producer waiting...");
            wait();
        }

        System.out.println("Adding items with the producer...");
        for(int i=0;i<capacity;i++) {
            buffer.add(i);
            System.out.println("Added value: " + i);
        }

        // wake up the consumer
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while(buffer.size() < capacity) {
            System.out.println("Buffer not full yet, consumer waiting...");
            wait();
        }

        while(!buffer.isEmpty()) {
            int item = buffer.remove(0);
            System.out.println("Consumer removes: " + item);
            Thread.sleep(300);
        }

        notify();
    }
}

class Consumer implements Runnable {
    private SharedBuffer sharedBuffer;

    public Consumer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        try {
            while(true) {
                this.sharedBuffer.consume();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Producer implements Runnable {
    private SharedBuffer sharedBuffer;

    public Producer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        try {
            while(true) {
                this.sharedBuffer.produce();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class SynchProducerConsumer {

    public static void main(String[] args) {
        var sharedBuffer = new SharedBuffer();

        Thread t1 = new Thread(new Producer(sharedBuffer));
        Thread t2 = new Thread(new Consumer(sharedBuffer));

        t1.start();
        t2.start();
    }
}
