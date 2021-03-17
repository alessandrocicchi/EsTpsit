/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estpsit;
import java.util.Random;
/**
 *
 * @author Alessandro
 */
public class EsTpsit {

    /**
     * @param args the command line arguments
     */
        // TODO code application logic here
        static Random random = new Random(System.currentTimeMillis());

    public static void main(String args[]) throws InterruptedException {

        // create object of unsafe counter
        ThreadUnsafeCounter badCounter = new ThreadUnsafeCounter();


        // setup thread1 to increment the badCounter 200 times
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 150; i++) {
                    badCounter.increment();
                    EsTpsit.sleepRandomlyForLessThan10Secs();

                }
            }
        });

        // setup thread2 to decrement the badCounter 200 times
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    badCounter.decrement();
                    EsTpsit.sleepRandomlyForLessThan10Secs();
                }
            }
        });

        // run both threads
        thread1.start();
        thread2.start();


        // wait for t1 and t2 to complete.
        thread1.join();
        thread2.join();


        // print final value of counter
        badCounter.printFinalCounterValue();

    }

    public static void sleepRandomlyForLessThan10Secs() {
        try {
            Thread.sleep(random.nextInt(1));
        } catch (InterruptedException ie) {
        }
         }
}

class ThreadUnsafeCounter {

    int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    void printFinalCounterValue() {
        System.out.println("counter is: " + count);
    }
}
