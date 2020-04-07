package com.lge.jv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// List<Integer> - Mutable

public class Program5 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);

        // 문제점: 컴파일 타임에 문제를 알 수 없다.
        list = Collections.unmodifiableList(list);
        list.add(20);
    }
}
