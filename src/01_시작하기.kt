import java.util.*

// 1) 코틀린 언어의 특징
// - 간결함
//  : 보일러플레이트(반드시 필요하지만, 반복적으로 발생하는 코드) 제거
// - 안전함(널 안전함)
//  : Nullable, Optional
//    null 체크를 안전하게 보장할 수 있는 기능
// - 상호운용성
//  : 자바로 작성한 코드를 코틀린에서 사용할 수있고, 코틀린으로 작성한 코드를
//    자바에서 사용할 수 있다.

/*
public class Progarm {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}
*/

// 2) main 함수를 만드는 방법
// fun main() {
/*
fun main(args: Array<String>) {
    println("Hello, Kotlin")
}
 */

// 3) 함수를 만드는 방법
// => 코틀린에서는 '전역 함수'를 허용한다.
//  자바 - 상속할 수 없고, 생성도 불가능한 클래스 안에 정적 메소드를 모아놓는 형태로 제작
//      => Collections, Arrays, Objects, ...
//  코틀린 - 전역 함수를 생성가능하다. 별도의 파일에 전역 함수를 모아서 작성하면 됩니다.
// fun 함수_이름(파라미터_이름 : 파라미터_타입) : 반환_타입
fun add(a: Int, b: Int): Int {
    // Objects.kt -> ObjectsKt.foo()
    return a + b
}

// 4) 코틀린 = 함수형 언어
//   * 순수 함수(Pure Function)
//     : 함수의 입력이 동일하면, 함수의 결과도 동일하다.
//    Java - void(존재하지 않음)
//    Kotlin - Unit(값이 존재하지 않음을 나타내는 값)

/*
// void foo() {}
fun foo() {
}
fun main() {
    println(foo())
}
*/

// 5) 타입 시스템
// * Java
//  - Primitive Type
//   : int, char, double, byte, float ...
//  - Reference Type
//   : class, interface, enum
//  문제점
//   * 원시타입을 Collection에 저장할 수 없다.
//    : 래퍼 클래스를 통해 처리해야 한다.
//   * 원시타입은 필드와 메소드를 사용할 수 없다.
// * Kotlin
//  => 모든 것은 객체 타입이다.
//  => 강력한 타입 제약 시스템을 가지고 있다.
//   : 암묵적인 타입 변환에 대한 부분을 거의 허용하지 않는다.
fun main() {
    42.toString()
    // 코틀린 컴파일러가 원시 타입으로 사용할 지, 래퍼 클래스로 취급할지를
    // 판단한다.

    // var vs val
    // var(변수) - 초기화 이후에 값의 변경이 가능하다.
    // val(값) - 초기화 이후에 값의 변경이 불가능하다.
    val n1: Int = 42
    var n2: Long = 42L

    n2 = n1.toLong()
}







