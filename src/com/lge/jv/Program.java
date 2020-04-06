package com.lge.jv;

/*
class User {
    protected String name;
    private int age;
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null)
//            return false;
//
//        if (this == obj)
//            return true;
//
//        if (!(obj instanceof User))
//            return false;
//
//        User user = (User)obj;
//        return Objects.equals(name, user.name) && age == user.age;
//    }
}
*/

// import ex3.User;

import ex3.User;

public class Program {
    public static void foo() {}

    public static void main(String[] args) {
        // System.out.println(foo());
//        User user = new User("Tom", 42);
//        user.name = "Bob";

        // User user = new User("Tom", 42, "Suwon");
        User user = new User("Tom");
    }
}
