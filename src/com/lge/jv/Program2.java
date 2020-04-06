package com.lge.jv;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

interface State extends Serializable {
}

interface View {
    State getCurrentState();

    void restoreState(State state);
}

class Button implements View {
    private String name;

    public Button(String name) {
        this.name = name;
    }

    // Memento Pattern
    // - 내부 클래스(Inner class) - Java's default
    //  문제점: 바깥 클래스의 암묵적인 참조가 내부 클래스 객체 내부에 생성됩니다.
    // - 중첩 클래스(Nested class) - Kotlin's default
    static class ButtonState implements State {
        // Button otherReference;
        String name;

        ButtonState(String name) {
            this.name = name;
        }
    }

    @Override
    public State getCurrentState() {
        return new ButtonState(name);
    }

    @Override
    public void restoreState(State state) {
        ButtonState s = (ButtonState) state;
        name = s.name;
    }
}


public class Program2 {
    public static void main(String[] args) throws Exception {
        Button button = new Button("MyButton");

        State s = button.getCurrentState();
        FileOutputStream fos = new FileOutputStream("state.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s);
    }
}








