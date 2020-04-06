package ex05

import java.io.*

// 05_클래스문법고급2
interface State : Serializable

interface View {
    var currentState: State
}

// inner class는 외부 클래스에 대한 암묵적인 참조가 존재한다.
//  => 누수가 발생할 수 있기 때문에, 대부분의 경우 중첩 클래스를 사용한다.
//  => Inner 클래스를 사용하는 경우
//    : Collection's Iterator
//      Model's Adapter
class Button(var name: String) : View {
    // Java   - 기본   / static
    // Kotlin - inner / 기본
    class ButtonState(var name: String) : State

    override var currentState: State
        get() {
            return ButtonState(name)
        }
        set(value) {
            val s = value as ButtonState
            name = s.name
        }
}

// Kotlin은 Checked Exception이 존재하지 않습니다.
// => 예외 처리가 강제되지 않습니다.

fun main() {
//    val button = Button("MyButton")
//    val fos = FileOutputStream("state.dat")
//    val oos = ObjectOutputStream(fos)
//    oos.writeObject(button.currentState)

    val fis = FileInputStream("state.dat")
    val ois = ObjectInputStream(fis);

    val s = ois.readObject() as State
    val button = Button("")
    button.currentState = s

    println(button.name)
}






