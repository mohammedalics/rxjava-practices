package practice.rxjava._03_transformations;

import java.util.ArrayList;
import java.util.List;

import practice.rxjava.DataGenerator;
import rx.Observable;

public class BufferExample {

    public static void main(String[] args) {
        Observable.from(DataGenerator.generateIntegers(20)).groupBy((i) -> {
            return 0 == (i % 2) ? "EVEN" : "ODD";
        }).subscribe((groupList) -> {
            System.out.println("Key: " + groupList.getKey() + "------");
            groupList.subscribe((x) -> {
               System.out.println(groupList.getKey() + ":" + x); 
            });
        });
        
        
        List<Integer> evenList = new ArrayList<Integer>();
        List<Integer> oddList = new ArrayList<Integer>(); 
        
        Observable.from(DataGenerator.generateIntegers(20)).groupBy((i) -> {
            return 0 == (i % 2) ? "EVEN" : "ODD";
        }).subscribe((groupList) -> {
            groupList.subscribe((x) -> {
               if("EVEN".equalsIgnoreCase(groupList.getKey())) {
                   evenList.add(x);
               } else {
                   oddList.add(x);
               }
            });
        });
        
        System.out.print("Evens ----- ");
        evenList.forEach(System.out::println);
        System.out.print("Odds ----- ");
        oddList.forEach(System.out::println);
    }

}
