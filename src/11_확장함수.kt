// 11_확장함수(Extension Function)
// => 코틀린의 가장 강력한 기능

// OCP - 개방 폐쇄의 원칙
// => 수정에는 닫혀 있고, 확장에는 열려 있어야 한다.
// => 새로운 기능이 추가되어도, 기존 코드는 수정되면 안된다.

// 상속을 통한 확장 - 수직 확장
// 1) 깨지기 쉬운 기반 클래스 문제
// 2) 상속이 불가능한 클래스에 대해서는 확장이 불가능하다.

// 수평 확장
// : 새로운 기능의 추가를 기존 클래스의 수정없이 할 수 있다.
//  C#    - Extension Method
// Swift  - Extension
// ObjC   - Category
// JS     - Prototype
// Kotlin - Extension Function
package ex11


// lastChar(text)
// => text.lastChar()
// fun lastChar(text: String): Char = text[text.length - 1]

fun String.lastChar(): Char = this[this.length - 1]
// => 첫번째 인자로 String 객체가 this라는 이름으로 전달된다.

/*
fun main() {
    val text = "hello"
    // val r = lastChar(text)
    val r = text.lastChar()
    println(r)
}

class Hello {
    // 메소드는 첫번째 인자로 객체의 참조가 암묵적으로 전달된다.
    // fun foo(this: Hello)
    fun foo() {

    }
}
*/

/*
class Button {
    var onClick: (() -> Unit)? = null

    fun click() {
        // onClick?()
        onClick?.invoke()
    }
}

fun close() { println("Global close") }

class Dialog {
    fun close() {

    }
}
fun main() {
    val dialog = Dialog()
    val button = Button()

    // button.onClick = ::close
    /*
    button.onClick = {
        dialog.close()
    }
    */
    button.onClick = dialog::close
    button.click()

    val email = "chansik.yun"
    val arr = email.split(".")
    println(arr)
}
*/


/*
// () -> Unit
fun close() { println("global close") }
class Dialog {
    // (Dialog) -> Unit
    fun close() {
        println("Dialog close")
    }
}

fun main() {
    var fp: () -> Unit = ::close
    fp()

    val dialog = Dialog()
    fp = dialog::close // bound method
    fp()

    // var fp: (Dialog) -> Unit = Dialog::close
    // val dialog = Dialog()
    // fp(dialog)
}
*/
// collection: Collection<T>,
fun <T> Collection<T>.xjoinToString(seperator: String,
                                    prefix: String,
                                    postfix: String): String {
    val result = StringBuilder(prefix)
    // for ((index, e) in this.withIndex()) {
    for ((index, e) in withIndex()) {
        if (index > 0)
            result.append(seperator)
        result.append(e)
    }
    result.append(postfix)s
    return result.toString()
}

// Android KTX(Kotlin Extension)

fun main() {
    val list = listOf(10, 20, 30, 40)
    // val r = xjoinToString(list, ",", "[", "]")
    val r = list.xjoinToString(",", "[", "]")
    println(r)
}




