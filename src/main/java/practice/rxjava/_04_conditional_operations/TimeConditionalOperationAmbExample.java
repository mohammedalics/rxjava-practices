package practice.rxjava._04_conditional_operations;

import practice.rxjava.DataGenerator;
import practice.rxjava.TimedEventSequence;
import rx.Observable;


public class TimeConditionalOperationAmbExample {

    public static void main(String[] args) throws InterruptedException {

        // Observable.emb
        TimedEventSequence<String> sequence1 = new TimedEventSequence<>(DataGenerator.generateAlphabet(), 20);
        TimedEventSequence<Integer> sequence2 = new TimedEventSequence<>(DataGenerator.generateIntegers(20), 40);

        // The first observable to emmit objects, the only observable to be considered.
        Observable.amb(sequence1.toObservable(), sequence2.toObservable()).subscribe(System.out::println);
        sequence1.start();
        sequence2.start();
        Thread.sleep(4000);
        sequence1.stop();
        sequence2.stop();
    }

}
