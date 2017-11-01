package practice.rxjava._05_connectable;

import practice.rxjava.TimeTicker;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;


public class ParallelObserveOnConnectableObservableExample {

    public static void main(String[] args) throws InterruptedException {

        // Connectable Observable
        TimeTicker timeTicker = new TimeTicker(500);
        timeTicker.start();
        ConnectableObservable connectableObservable = timeTicker.toObservable().publish();

        // First subscriber
        connectableObservable
                // run on different thread!
                .observeOn(Schedulers.computation())
                .subscribe((o) -> {
            System.out.println("Thread " + Thread.currentThread().getName() + ", Observer_1: " + o);
        });

        // Second subscriber
        connectableObservable
                // run on different thread!
                .observeOn(Schedulers.computation())
                .subscribe((o) -> {
            System.out.println("Thread " + Thread.currentThread().getName() + ", Observer_2: " + o);
        });

        System.out.println("Waiting to be connected...");
        Thread.sleep(4000);
        System.out.println("Connected!");
        connectableObservable.connect();
        Thread.sleep(4000);
        timeTicker.stop();
    }

}
