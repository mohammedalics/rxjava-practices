package practice.rxjava._04_conditional_operations;

import practice.rxjava.DataGenerator;
import practice.rxjava.TimedEventSequence;


public class TimeConditionalOperationSkipUntilExample {

    public static void main(String[] args) throws InterruptedException {

        // Observable.emb
        TimedEventSequence<String> sequence1 = new TimedEventSequence<>(DataGenerator.generateAlphabet(), 20);
        TimedEventSequence<Integer> sequence2 = new TimedEventSequence<>(DataGenerator.generateIntegers(20), 40);

        // Observable.skipUntil
        sequence1.toObservable().skipUntil(sequence2.toObservable()).subscribe(System.out::println);
        sequence1.start();
        sequence2.start();
        Thread.sleep(4000);
        sequence1.stop();
        sequence2.stop();

    }

}
