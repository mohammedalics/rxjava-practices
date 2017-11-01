package practice.rxjava._03_transformations;

import practice.rxjava.DataGenerator;
import rx.Observable;

public class ScanExample {

    public static void main(String[] args) {

        Observable.from(DataGenerator.generateAlphabet()).scan(new StringBuilder(), (accumBuffer, nextLetter) -> {
            return accumBuffer.append(nextLetter);
        }).subscribe(System.out::println);

        Observable.from(DataGenerator.generateAlphabet()).scan(new StringBuilder(), (accumBuffer, nextLetter) -> {
            return accumBuffer.append(nextLetter);
        }).last().subscribe(System.out::println);

    }
}
