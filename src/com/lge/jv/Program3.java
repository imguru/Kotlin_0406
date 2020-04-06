package com.lge.jv;

// Cloneable - mark-up interface
//      : 타입을 체크하기 위한 목적으로만 사용한다.

class User implements Cloneable {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 복사 생성자
    public User(User rhs) {
        this.name = rhs.name;
        this.age = rhs.age;
    }

    // 0) clone을 제공하는 모든 객체는 Cloneable의 인터페이스를 구현해야 한다.
    // 1) protected -> public 변경
    // 2) 예외를 제거
    // 3) 원시 타입은 Object.clone()을 통해,
    //    참조 타입은 해당 객체가 제공하는 clone()을 통해 복제해야 한다.
    // 4) Generic - 공변 반환의 법칙
    //            : 부모의 메소드의 반환 타입을 하위 타입으로 변환하는 것이 가능하다.
    @Override
    public User clone() {
        try {
            User copy = (User)super.clone();
            // copy.name = name.clone()
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

public class Program3 {
    public static void main(String[] args) {
        User user = new User("Tom", 42);
        User copy = user.clone();
        // 1. 객체를 복제하는 기능은 기본적으로 제공되지 않습니다.
        // 2. Object.clone이라는 기능을 구현해야 합니다.
        System.out.println(user);
        System.out.println(copy);
    }
}

// https://github.com/imguru/Kotlin_0406