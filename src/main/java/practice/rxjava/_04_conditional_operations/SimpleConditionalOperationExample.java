package practice.rxjava._04_conditional_operations;

import practice.rxjava.DataGenerator;
import rx.Observable;

public class SimpleConditionalOperationExample {

    public static void main(String[] args) {
        // Default is empty so if the Observable is empty, the observable will emmit it.
        Observable.empty().defaultIfEmpty("Hello World").subscribe(System.out::println);

        System.out.println();

        // Another example for DefaultIfEmpty.
        Observable.from(DataGenerator.generateAlphabet()).defaultIfEmpty("Hello World").first()
                .subscribe(System.out::println);

        System.out.println();

        // SkipWhile is skipping emitted objects until the first object meet the predicate.
        Observable.from(DataGenerator.generateIntegers(30)).skipWhile((i) -> {
            return i < 8;
        }).subscribe(System.out::println);

        System.out.println();

        // It considers the emitted objects until the first one meet the predicate.
        Observable.from(DataGenerator.generateIntegers(30)).takeWhile((i) -> {
            return i < 8;
        }).subscribe(System.out::println);

        System.out.println();
    }

}
