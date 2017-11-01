package practice.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {

    public static List<Double> generateDoubles(int numberOfElements) {
        return Stream.generate(Math::random).limit(numberOfElements).collect(Collectors.toList());
    }

    public static List<Integer> generateIntegers(int numberOfElements) {
        return Stream.generate(Math::random).map(i -> {
            return (int) (i * 10);
        }).limit(numberOfElements).collect(Collectors.toList());
    }

    public static List<String> generateAlphabet() {
        List<String> alphabetList = new ArrayList<String>();
        alphabetList.add("a");
        alphabetList.add("b");
        alphabetList.add("c");
        alphabetList.add("d");
        alphabetList.add("e");
        alphabetList.add("f");
        alphabetList.add("g");
        alphabetList.add("h");
        alphabetList.add("i");
        alphabetList.add("j");
        alphabetList.add("k");
        alphabetList.add("l");
        alphabetList.add("m");
        alphabetList.add("n");
        alphabetList.add("o");
        alphabetList.add("p");
        alphabetList.add("q");
        alphabetList.add("r");
        alphabetList.add("s");
        alphabetList.add("t");
        alphabetList.add("u");
        alphabetList.add("v");
        alphabetList.add("w");
        alphabetList.add("x");
        alphabetList.add("y");
        alphabetList.add("z");
        return alphabetList;
    }

    public static void main(String[] args) {
        System.out.println(generateIntegers(10));
    }
}
