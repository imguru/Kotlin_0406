package ex10
// 10_함수형 프로그래밍
// => 함수를 일급 시민(First-class Citizens)으로 취급된다.
// => 함수를 참조할 수 있다.
// 1) 변수에 담을 수 있어야 한다.
// 2) 반환 값을 통해 전달할 수 있어야 한다.
// 3) 인자를 통해 전달할 수 있어야 한다.
// 4) 함수를 실행 시간에 생성할 수 있어야 한다.
// 5) 익명으로 함수를 생성할 수 있어야 한다.
//  => Kotlin, Swift, Scala, Javascript

/*
fun add2(a: Int, b: Int) : Int {
    return a + b
}
*/

// 단일 표현식 함수:
// => 타입 추론이 가능합니다.
// => 반환값에 추론 또는 우항의 타입의 추론이 가능하다.
fun add(a: Int, b: Int) = a + b
fun sub(a: Int, b: Int) = a - b

// 함수의 타입
//  : 함수의 타입은 인자와 반환타입에 의해서 결정된다.
//  => 함수의 타입은 함수의 시그니처에 의해서 결정된다.
// add: (Int, Int) -> Int
// sub: (Int, Int) -> Int
fun test(a: Int, b: Char): Double = 3.14

/*
fun main() {
    // 글로벌 함수 - ::add
    // 클래스 내부의 메소드 - Sample::close

    // * 실행 시간에 어떤 함수를 호출할 지 결정할 수 있다.
    // var fp: (Int, Int) -> Int = ::add
    var fp = ::add

    // JVM - KFunction{인자의 개수}<Arg1, Arg2, Ret>
    var fp2 = ::test

    var result = fp(10, 20)
    println(result)

    fp = ::sub
    result = fp(30, 40)
    println(result)

    // var n : Int = 42
    // var n = listOf<Int>() // ArrayList<Int>()
}
*/

fun printArea(width: Int, height: Int) {
    // 지역 함수를 만드는 것도 가능합니다.
    // fun calcArea(width: Int, height: Int) = width * height

    // 지역 함수에서는 인자의 전달을 암묵적으로 수행할 수 있다. => 클로저
    // ; 함수의 스코프 외부의 선언한 매개변수나 변수에 암묵적으로 접근하는 것이
    //   가능합니다.
    fun calcArea() = width * height

    val result = calcArea()
    println("Area is $result")
}

fun String.lastChar(): Char = this[this.length - 1]


fun main() {
    printArea(10, 300)
}












