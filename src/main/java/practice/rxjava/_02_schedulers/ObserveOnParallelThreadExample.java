package practice.rxjava._02_schedulers;

import java.util.List;

import practice.rxjava.DataGenerator;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ObserveOnParallelThreadExample {
    public static void main(String[] args) throws InterruptedException {

        // Thread lock object
        Object waitMonitor = new Object();

        // Sync on waitMonitor Object
        synchronized (waitMonitor) {
            System.out.println("Driving thread: " + Thread.currentThread().getName());

            // Create List of integers.
            List<Integer> intList = DataGenerator.generateIntegers(10);

            // Create Observable from Integer list.
            Observable<Integer> observable = Observable.from(intList);

            // Subscribe an Observer on through a newThread Scheduler.
            observable.observeOn(Schedulers.io()).subscribe((i) -> {
                // onNext
                System.out.println("onNext thread - START: " + Thread.currentThread().getName());
                System.out.println(i);
                System.out.println("onNext thread - END: " + Thread.currentThread().getName());

            }, (t) -> {
                // onError
                t.printStackTrace();
            }, () -> {
                // onCompleted
                System.out.print("onCompleted()");
                synchronized (waitMonitor) {
                    waitMonitor.notifyAll();
                }
            });

            // Wait to make sure that the new thread had been finished. 
            synchronized (waitMonitor) {
                waitMonitor.wait();
            }
        }
    }
}
