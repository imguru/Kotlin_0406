package com.lge.jv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

// List<Integer> - Mutable

@FunctionalInterface
interface Foo {
    void foo();

    default void goo() {
    }
}

public class Program5 {
    public static List<Integer> filter(List<Integer> data,
                                       Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer e : data) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        // filter(list)
        // 정책으로 전달되는 익명의 인터페이스가 메소드가 한개만 존재할 경우,
        // 람다 표현식을 사용할 수 있다. - Java 8
        List<Integer> result = filter(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        });

        result = filter(list, (Integer integer) -> {
            return integer % 2 == 0;
        });

        // 인자 타입 추론이 가능합니다.
        result = filter(list, (integer) -> {
            return integer % 2 == 0;
        });

        // 인자가 한개인 경우, 괄호도 생략이 가능합니다.
        result = filter(list, integer -> {
            return integer % 2 == 0;
        });

        // 블록의 구현이 한줄인 경우, 생략이 가능하다.
        result = filter(list, integer -> integer % 2 == 0);


        for (Integer e : result)
            System.out.println(e);

    }
}
/*
public class Program5 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);

        // 문제점: 컴파일 타임에 문제를 알 수 없다.
        list = Collections.unmodifiableList(list);
        list.add(20);
    }
}
*/
