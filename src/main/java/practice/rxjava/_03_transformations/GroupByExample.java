package practice.rxjava._03_transformations;

import practice.rxjava.DataGenerator;
import rx.Observable;

public class GroupByExample {

    public static void main(String[] args) {
        Observable.from(DataGenerator.generateIntegers(1000)).buffer(10).subscribe((list) -> {
            System.out.println("----------");
            int count = 1;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                System.out.println("" + (count++) + ": " + list.get(i));
            }
        });
    }
}
