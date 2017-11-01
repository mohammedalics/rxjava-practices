package practice.rxjava._01_creation;

import java.util.List;

import practice.rxjava.DataGenerator;

import java.util.concurrent.FutureTask;

import rx.Observable;
import rx.schedulers.Schedulers;

public class FutureCreationExamples {

    public static void main(String[] args) {

        // Create a future task.
        FutureTask<List<Integer>> future = new FutureTask<>(() -> {
            return DataGenerator.generateIntegers(10);
        });

        // Create an Observable from the future task.
        Observable<List<Integer>> futureObservable = null;
        futureObservable = Observable.from(future);

        // Create a scheduler that runs the future task in computation
        // scheduler.
        Schedulers.computation().createWorker().schedule(() -> {
            future.run();
        });

        // Subscribe an Action to the observable to be called after the
        // scheduler runs.
        futureObservable.subscribe(System.out::println);
    }
}
