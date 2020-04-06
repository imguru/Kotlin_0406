// 05_클래스문법고급
// Java - Interface vs Abstract Class
//  1) Interface는 필드를 정의할 수 없다.
//  2) Interface는 메소드의 구현을 제공할 수 없다. - Java 8 허용
//    => default method

// Kotlin
//  1) Property도 인터페이스로 명세할 수 있다.
//  2) 기본 구현도 제공할 수 있다.
interface User {
    val fullName: String

    fun foo() {}
}

//  => Backing Field가 있는 프로퍼티
class MyUser2(override val fullName: String) : User

//  => Backing Field가 없는 프로퍼티
class MyUser : User {
    override val fullName: String
        get() {
            return "Gildong Hong"
        }
}

interface Focusable {
    fun onFocus() {}

    fun showOff(): String {
        return "I'm Focusable"
    }
}

interface Clickable {
    fun onClick() {}

    fun showOff(): String {
        return "I'm Clickable"
    }
}

// 오류: 부모의 인터페이스의 기본 구현이 동일한 이름을 가질 경우, 모호성 에러가 발생합니다.
// 1. open: 상속을 허용하는 클래스
abstract class Button(val name: String) : Clickable, Focusable {
    override fun showOff(): String {
        return "${super<Clickable>.showOff()} - ${super<Focusable>.showOff()}"
    }

    // 기본적으로 오버라이딩 금지되어 있다.
    // 1. open fun move(x: Int, y: Int) {}

    // 2. abstract
    abstract fun move(x: Int, y: Int)
}

// 2.
// extends,   ->   class DerivedClass : BaseClass()
// implements ->   class DerivedClass : Interface

// Effective Java
//  => 상속을 위한 설계와 문서를 갖추거나 그럴 수 없다면, 상속을 금지해라.
//  => 코틀린은 class Button -> final class Button
//     기본적으로 상속이 금지되어 있습니다.
//  => 상속의 문제점
//    : 기반 클래스를 변경하는 경우 하위 클래스의 동작이 예기치 않게 변경될 수 있다.

class MouseButton : Button("MyButton") {
    override fun move(x: Int, y: Int) {

    }
}




















