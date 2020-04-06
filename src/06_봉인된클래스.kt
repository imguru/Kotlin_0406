// 06_봉인된클래스
package ex06

import java.lang.IllegalArgumentException

// 1. enum
// C's enum: 이름있는 상수값
// Kotlin / Java's enum: 객체
// 차이점: enum, open - soft keyword
enum class Color {
    RED, ORANGE, YELLOW, GREEN, INDIGO
}

// enum은 객체이기 때문에, 프로퍼티나 메소드를 가질 수 있습니다.
enum class Color2(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    BLUE(0, 0, 255)
    ; // 메소드를 정의하기 위해서는 반드시 세미콜론을 작성해야 합니다.

    fun rgb(): Int {
        return (r * 255 + g) * 255 + b
    }
}

/*
val a = if (a == 0) 10 else 40

*/

// statement(문) - 결과값이 존재하지 않는다.
// expression(식) - 결과값이 존재한다.
fun getName(color: Color): String {
    // Kotlin에서는 switch - case 문법이 사라졌습니다.
    // => when
//    val result = when(color) {
//        Color.RED -> "Red"
//        Color.ORANGE -> "Orange"
//        else -> "Unknown"
//    }

    // 여러개를 지정 가능하다.
    val result = when (color) {
        Color.RED, Color.ORANGE -> "Red"
        else -> "Unknown"
    }

    return result
}

fun mix(c1: Color, c2: Color): Color {
    return when (setOf(c1, c2)) { // Set<Color>
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        else -> throw Exception("Dirty Color")
    }
}

/*
fun main() {
    val enum: String = ""
    val color = Color2.RED
    println(color.rgb())
}
*/
// 문제점: 새로운 Expr 기반의 클래스가 생성될 경우, 예외가 발생한다.
// 해결책: Num, Sum 이외의 Expr 하위 클래스의 생성을 막고 싶다.
//     => 봉인된 클래스: 계층 확장 제한 문법

sealed class Expr {
    // Kotlin 1.0: 봉인된 클래스는 반드시 Expr의 중첩된 클래스로 제공해야 한다.
    //        1.1: 같은 파일내에서까지 허용한다.
//    class Num(val value: Int) : Expr()
//    class Sum(val left: Num, val right: Num) : Expr()
}

class Num(val value: Int) : Expr()
class Sum(val left: Num, val right: Num) : Expr()
// class Null(): Expr()

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
    }
}

/*
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Num, val right: Num) : Expr

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else ->
            throw IllegalArgumentException("Unknown type")
    }
}

*/

fun main() {
    val sum = Sum(Num(10), Num(20))
    val result = eval(sum)
    println(result)
}


















