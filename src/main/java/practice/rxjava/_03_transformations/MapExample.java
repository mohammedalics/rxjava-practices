package practice.rxjava._03_transformations;

import practice.rxjava.DataGenerator;
import rx.Observable;

public class MapExample {

    public static void main(String[] args) {

        Observable.from(DataGenerator.generateAlphabet()).map(String::toUpperCase).subscribe(System.out::println);
        Observable.from(DataGenerator.generateAlphabet()).flatMap((letter) -> {
            String[] strings = { letter.toUpperCase(), letter.toLowerCase() };
            return Observable.from(strings);

        }).subscribe(System.out::println);
    }

}
