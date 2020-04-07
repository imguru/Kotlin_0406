package ex13_2

// 함수를 반환하는 함수(고차 함수)
//  최종 목적: 실행 시간에 함수를 만드는 기술

// fun isEven(e: Int) = e % 2 == 0
// fun isOdd(e: Int) = e % 2 == 1

fun modulo2(r: Int): (Int) -> Boolean = { e: Int ->
    e % 2 == r
}

fun modulo(k: Int, r: Int): (Int) -> Boolean = { e: Int ->
    e % k == r
}

/*
fun main() {
    val isOdd = modulo(2, 1)
    val isEven = modulo(2, 0)

    val list = listOf(1, 2, 3, 4, 5)
    val result = list.filter(isOdd)
    println(result)
}
 */


// foo 함수는 'String을 인자로 받고, String을 반환하는 함수'를 반환하는 함수입니다.
// => 함수를 반환하는 함수를 만들 때는 단일 표현식의 타입 추론이 매우 유용합니다.
/*
fun foo(): (String) -> String = { str: String ->
    str.reversed()
}

fun goo(): (String) -> String {
    return { str: String ->
        str.reversed()
    }
}

fun main() {
    val f: (String) -> String = foo()
    val result = f("hello")
    println(result)
}
*/