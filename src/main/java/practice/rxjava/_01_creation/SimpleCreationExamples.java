package practice.rxjava._01_creation;

import practice.rxjava.DataGenerator;
import rx.Observable;

/**
 * 
 */
public class SimpleCreationExamples {
    public static void main(String[] args) {
        Observable<Integer> observable = null;
        // Create Observable object from List of Integers.
        observable = Observable.from(DataGenerator.generateIntegers(10));
        // Subscribe an action. This action fires on each emitted integer. 
        observable.subscribe(System.out::println);
    }
}
