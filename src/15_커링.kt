import java.lang.Appendable
import kotlin.math.log

// 15_커링
// 커링(Currying)
// => 다중 인수를 갖는 함수를 단일 함수를 갖는 함수들의 함수열로 변경하는 것

// sum(10, 20) -> sum(10)(20)

fun sum(x: Int, y: Int): Int = x + y
/*
// Scala
fun sum(x: Int)(y: Int): Int = x + y
*/

// Kotlin
fun sum(x: Int): (Int) -> Int = { y: Int ->
    x + y
}

/*
fun main() {
    // val result = sum(10, 20)
    val result = sum(10)(20)
    println(result)
}
*/

// 1) 지연 함수
//   : 함수의 실행을 지연할 수 있다.

// 2) 부분 적용
//  : 함수의 인자를 고정하는 기술
//  => C++ bind
fun compute(logger: (String) -> Unit) {
    logger("Compute start!")
    logger("Compute...")
    logger("Compute finish!")
}
enum class Level { INFO, WARN, CRITICAL }

fun<P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1 ->
    { p2 ->
        { p3 ->
            this(p1, p2, p3)
        }
    }
}

// 기존의 로그 라이브러리
fun log2(level: Level, appendable: Appendable, message: String) {
    appendable.appendln(message)
}

fun main() {
    // 커링 없이 사용하였을 때
    compute { message ->
        log2(Level.INFO, System.out, message)
    }

    val logger = ::log2.curried()(Level.INFO)(System.out)
    compute(logger)
}








