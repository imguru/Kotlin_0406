package com.lge.jv;

// Singleton
//  : 오직 한개의 객체를 만들고, 언제 어디서든 동일한 방법으로 접근할수 있는 객체
// Version 1
//  static final 변수에 대한 초기화는 멀티 스레드 안전하다. - JLS 보장한다.
/*
class Mouse {
    private static final Mouse INSTANCE = new Mouse();

    // 1. private 생성자
    private Mouse() {
    }

    // 2. 정적 메소드
    public static Mouse getInstance() {
        return INSTANCE;
    }
}
*/

// Version 2. 미리 생성하는 것이 아니라, 처음 접근하는 시점에 생성되는 싱글톤
/*
class Mouse {
    // IODH - Initialization-on-demand holder
    // 1. 중첩 클래스의 인스턴스는 처음 접근되는 시점에 생성된다.
    // 2. static final field는 스레드 안전하게 생성된다.
    private static class Singleton {
        private static Mouse INSTANCE = new Mouse();
    }

    // 1. private 생성자
    private Mouse() {
    }

    // 2. 정적 메소드
    public static Mouse getInstance() {
        return Singleton.INSTANCE;
    }



    // DCLP - Double Checked Locking Pattern

    /*
    //  문제점: 자바에서는 잘 동작하지만, 코드가 선언적이지 않다.
    public static Mouse getInstance() {
        if (sInstance == null) {
            synchronized (Mouse.class) {
                if (sInstance == null)
                    sInstance = new Mouse();
            }
        }

        return sInstance;
    }
    */


    /*
    // 아래 코드는 잘 동작하지만, 매번 동기화를 수행해야 하는 오버헤드가 있습니다.
    public synchronized static Mouse getInstance() {
        if (sInstance == null)
            sInstance = new Mouse();

        return sInstance;
    }
    */

// 아래 코드는 스레드 안전하지 않다.
    /*
    public static Mouse getInstance() {
        if (sInstance == null)
            sInstance = new Mouse();
        return sInstance;
    }

}
*/

import ex5.Mouse;
import ex5.User2;

public class Program4 {
    public static void main(String[] args) {
        System.out.println(Mouse.INSTANCE.getName());

        // User2.sName
        // User2.Companion.getSName()
        // User2.Hello.getSName();
    }
}
