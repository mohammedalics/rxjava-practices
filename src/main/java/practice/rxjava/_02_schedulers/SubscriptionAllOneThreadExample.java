package practice.rxjava._02_schedulers;

import java.util.List;

import practice.rxjava.DataGenerator;
import rx.Observable;

public class SubscriptionAllOneThreadExample {

    public static void main(String[] args) {

        // Print the current thread name
        System.out.println("Driving thread: " + Thread.currentThread().getName());

        // Get list of Integers
        List<Integer> intList = DataGenerator.generateIntegers(10);

        // Create Observable of Integers
        Observable<Integer> observable = Observable.from(intList);

        // Subscribe an Observer
        observable.subscribe((i) -> {
            System.out.println("onNext thread - START: " + Thread.currentThread().getName());
            System.out.println(i);
            System.out.println("onNext thread - END: " + Thread.currentThread().getName());

        }, (t) -> {
            t.printStackTrace();
        }, () -> {
            System.out.print("onCompleted()");
        });
    }

}
