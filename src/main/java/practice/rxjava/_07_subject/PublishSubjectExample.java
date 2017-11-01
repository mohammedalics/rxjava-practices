package practice.rxjava._07_subject;

import practice.rxjava.DataGenerator;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class PublishSubjectExample {

    public static void main(String[] args) throws InterruptedException {

        Object signal = new Object();
        synchronized (signal) {

            // PublishSubjects are good for multicasting events to subscribers.
            // The subscribers will see only the events that happen AFTER they
            // subscribe.
            // Create an PublishSubject using its factory method
            PublishSubject<String> subject = PublishSubject.create();

            // We want to subscribe to this subject
            subject.subscribe(
                    (letter) -> {
                        System.out.println("Subscriber 1: " + letter);

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        if( letter.equals( "l" ) ) {
                            synchronized(signal) {
                                signal.notify();
                            }
                        }
                    }, (t) -> {
                        t.printStackTrace();
                    }, () -> {
                        System.out.println("Subscriber 1: onCompleted");
                    }
            );

            // Next we create an observable out of the alphabet...
            // For each letter, we will publish an event to the PublishSubject
            Observable.from(DataGenerator.generateAlphabet())
                    .subscribeOn(Schedulers.computation())
                    .subscribe(
                            (letter) -> {
                                // ...for each letter, we will emit an event to the subject
                                subject.onNext(letter);
                            },
                            (t) -> {
                                subject.onError(t);
                            },
                            // ...once complete...we tell the subject
                            () -> {
                                subject.onCompleted();
                                synchronized( signal ) {
                                    signal.notify();
                                }
                            });

            signal.wait();

            subject.subscribe(
                    (letter) -> {
                        System.out.println("Subscriber 2: " + letter);
                    },
                    (t) -> {
                        subject.onError(t);
                    },
                    () -> {
                        System.out.println("Subscriber 2: onCompleted");
                    }
            );

            signal.wait();
        }
        System.exit(0);
    }
}
