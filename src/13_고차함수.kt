import com.lge.jv.Program5

import java.util.function.Predicate
//interface Predicate<T> {
//    fun test(e: T): Boolean
//}

// 13_고차함수(Higher Order Function)
// : 함수를 인자로 전달 받거나, 함수를 반환하는 함수

// 활용
// 1) 다양한 정책에서 동작하는 함수의 코드 중복을 없앨 수 있다.
//   => 함수의 재사용성 증가


// - Collection
//  Kotlin:  List,         Map,         Set  - Immutable Interface
//        MutableList  MutableMap MutableSet - Mutable Interface
fun filterOdd(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (e % 2 == 1)
            result.add(e)
    }
    return result
}

fun filterEven(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (e % 2 == 0)
            result.add(e)
    }
    return result
}

// Version 2. Kotlin - 함수 기반의 정책 분리 방법
//   : C++, C, C#, Swift ...

fun filter(data: List<Int>, test: (Int) -> Boolean): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        // if (e % 2 == 0)
        if (test(e))
            result.add(e)
    }
    return result
}

// (Int) -> Boolean
fun isOdd(e: Int) = e % 2 == 1
fun isEven(e: Int) = e % 2 == 0

fun main() {
    val list = listOf(1, 2, 3, 4, 5)

    var result = filter(list, ::isOdd)
    println(result)

    result = filter(list, ::isEven)
    println(result)

    // 익명 함수 - 이름이 없는 함수이다.
    //   주의: 람다 표현식이 아닙니다.
    result = filter(list, fun(e: Int): Boolean {
        return e % 2 == 0
    })
    result = filter(list, fun(e: Int) = e % 2 == 0)

    // 람다 표현식: 실행 가능한 코드 블록을 참조하는 기술
    result = filter(list, { e: Int ->
        e % 2 == 0
    })

    // 함수의 마지막 인자가 함수타입이면, 람다 블록을
    // 외부로 분리할 수 있습니다.
    result = filter(list) { e: Int ->
        e % 2 == 0
    }

    // 람다의 첫번째 인자는 이름을 지정하지 않을 경우, it 라는 이름으로
    // 암묵적으로 지정됩니다.
    result = filter(list) { it % 2 == 0 }


    result = Program5.filter(list, object: Predicate<Int> {
        override fun test(p0: Int): Boolean = p0 % 2 == 0
    })

    // SAM(Single Abstract Method) 지원
    // => 자바의 SAM 인터페이스에 대해서, 람다식을 허용한다.
    result = Program5.filter(list) { integer: Int ->
        integer % 2 == 0
    }

    // 1.4 에서는 SAM 지원이 코틀린 클래스에 대해서 허용될 예정입니다.
//    result = filter2(list, object : Predicate<Int> {
//        override fun test(p0: Int): Boolean = p0 % 2 == 0
//    })

    println(result)
}

fun filter2(data: List<Int>, predicate: Predicate<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (predicate.test(e))
            result.add(e)
    }
    return result
}

// * 변하지 않는 전체 알고리즘에서 변해야 하는 정책은 분리되어야 한다.
//   => 공통성과 가변성을 분리 설계
//   => 함수(메소드)에서 정책을 분리하는 방법

// Version 1. Java - 동작 파라미터화 설계(Interface)

// 조건자
/*
interface Predicate<T> {
   fun test(e: T): Boolean
}

fun filter(data: List<Int>, predicate: Predicate<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (predicate.test(e))
            result.add(e)
    }
    return result
}

class OddPredicate : Predicate<Int> {
    override fun test(e: Int): Boolean = e % 2 == 1
}

class EvenPredicate : Predicate<Int> {
    override fun test(e: Int): Boolean = e % 2 == 0
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5)

    var result = filter(list, OddPredicate())
    result = filter(list, EvenPredicate())
//    var result = filterOdd(list)
//    result = filterEven(list)

    println(result)
}

*/


