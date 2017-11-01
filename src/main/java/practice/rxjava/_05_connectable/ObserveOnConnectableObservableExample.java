package practice.rxjava._05_connectable;

import practice.rxjava.DataGenerator;
import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;


public class ObserveOnConnectableObservableExample {

    public static void main(String[] args) throws InterruptedException {

        // Connectable Observable
        ConnectableObservable connectableObservable = Observable.from(DataGenerator.generateAlphabet()).publish();

        // First subscriber
        connectableObservable
                // run on different thread!
                .observeOn(Schedulers.computation())
                .subscribe((o) -> {
            System.out.println("Thread " + Thread.currentThread().getName() + ", Observer_1: " + o);
        });

        // Second subscriber
        connectableObservable.subscribe((o) -> {
            System.out.println("Thread " + Thread.currentThread().getName() + ", Observer_2: " + o);
        });

        System.out.println("Waiting to be connected...");
        Thread.sleep(3000);
        System.out.println("Connected!");
        connectableObservable.connect();

    }

}
