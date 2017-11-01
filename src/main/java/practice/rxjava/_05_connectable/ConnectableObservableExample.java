package practice.rxjava._05_connectable;

import practice.rxjava.DataGenerator;
import rx.Observable;
import rx.observables.ConnectableObservable;


public class ConnectableObservableExample {

    public static void main(String[] args) throws InterruptedException {

        // Connectable Observable
        ConnectableObservable connectableObservable = Observable.from(DataGenerator.generateAlphabet()).publish();

        connectableObservable.subscribe(System.out::println);
        System.out.println("Waiting to be connected...");
        Thread.sleep(3000);
        System.out.println("Connected!");
        connectableObservable.connect();

    }

}
