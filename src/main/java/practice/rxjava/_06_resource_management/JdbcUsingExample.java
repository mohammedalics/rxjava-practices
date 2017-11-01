package practice.rxjava._06_resource_management;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

public class JdbcUsingExample {

    public static void main(String[] arg) {

        // Create a simple error handler that will emit the stack trace for a given exception
        Action1<Throwable> simpleErrorHandler = (t) -> {
            t.printStackTrace();
        };

        // Initialize the MyDB Derby database...this also resets its contents
        MyDB.init();

        // Resource Factory function to create a MyDB connection and wrap it
        // in a ConnectionSubscription
        Func0<ConnectionSubscription> resourceFactory = () -> {
            return new ConnectionSubscription(MyDB.createConnection());
        };

        // Observable Factory function to create the resultset that we want.
        Func1<ConnectionSubscription, Observable<String>> greekAlphabetList = (connectionSubscription) -> {
            return MyDB.selectGreekAlphabet(connectionSubscription);
        };

        Action1<ConnectionSubscription> disposeAction = (connectionSubscription) -> {
            System.out.println("Calling disposeAction");
            connectionSubscription.unsubscribe();
        };

        Observable<String> t = Observable.using(resourceFactory, greekAlphabetList, disposeAction);

        t.subscribe(
                (letter) -> {
                    System.out.println(Thread.currentThread().getName() + " - " + letter);
                },
                simpleErrorHandler,
                () -> {
                    System.out.println( "onCompleted" );
                }
        );

        System.exit(0);
    }
}
