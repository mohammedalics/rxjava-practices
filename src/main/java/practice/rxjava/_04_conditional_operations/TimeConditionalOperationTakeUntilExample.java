package practice.rxjava._04_conditional_operations;

import practice.rxjava.DataGenerator;
import practice.rxjava.TimedEventSequence;


public class TimeConditionalOperationTakeUntilExample {

    public static void main(String[] args) throws InterruptedException {

        // Observable.emb
        TimedEventSequence<String> sequence1 = new TimedEventSequence<>(DataGenerator.generateAlphabet(), 20);
        TimedEventSequence<Integer> sequence2 = new TimedEventSequence<>(DataGenerator.generateIntegers(20), 40);

        // Observable.takeUntil
        sequence1.toObservable().takeUntil(sequence2.toObservable()).subscribe(System.out::println);
        sequence1.start();
        sequence2.start();
        Thread.sleep(4000);
        sequence1.stop();
        sequence2.stop();
    }

}
