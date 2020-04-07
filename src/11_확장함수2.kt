package ex11_2

// 확장 함수가 동작하는 방식
open class View {
    fun click() = println("View clicked")
}

class Button : View() {
    // override fun click() = println("Button clicked")
}

// String - Immutable Object: 객체가 생성된 이후에 변경되지 않는 객체
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) = setCharAt(length - 1, value)

// Backing Field가 없는 property에 대해서만 확장을 할 수 있습니다.
var Button.name: String
    get() = "MyButton"
    set(value) {
        // field = value
    }

//    get() {
//        return "MyButton"
//    }

// 정적 바인딩으로 동작하기 때문에, 참조의 타입에 의해서 호출되는 함수가 결정된다.
// 동일한 이름의 메소드가 이미 존재할 경우, 확장 함수는 호출되지 않는다.
fun View.click() = println("View2 clicked")
// fun click(view: View) = println("View2 clicked")

// fun Button.click() = println("Button clicked")

// 언어 함수 바인딩 방식
// 1. Static binding: 컴파일 타입에 컴파일러가 결정한다.
// 2. Dynamic binding: 런타임에서 참조가 담고 있는 객체의 타입을 조사해서, 호출한다.
//   (Virtual)

fun main() {
    val view: Button = Button()
    // Upcasting: 자식 클래스의 객체를 부모 타입의 레퍼런스 타입을 통해 암묵적으로 가르킬 수 있다.
    //  is-a: Button is a View

    view.click()
    println(view.name)
}


/*
class User {
    // protected var name: String = "Tom"
    internal var name: String = "Tom"
    var age: Int = 42
}

// User 클래스가 외부에서 작성된 클래스인 경우 확장 함수가 유용합니다.
// 그 외의 경우에는 사용하지 않는 것이 좋습니다.
// fun foo(user: User) { println("${user.name} - ${user.age}") }
fun User.foo() { println("$name - $age") }
// * 확장 함수는 결국 외부의 함수이기 때문에, User가 공개한 데이터 또는 메소드에만 접근 가능하다.
//   protected 접근이 불가능하다.
fun main() {
    val user = User()
    user.foo()
}
*/


/*
import ex10.lastChar

// import 에서 이름 충돌이 발생할 경우, aliasing 기능을 통해 해결할 수 있습니다.
import ex11.lastChar as lc
import ex09.UIElement as Element


// 11_확장함수2
fun main() {
    val c = "Kotlin".lastChar()
    var c2 = "Kotlin".lc()
}
*/








