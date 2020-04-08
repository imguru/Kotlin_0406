package ex18

// 메모이제이션(캐시) - DP
// => 입력(인자)가 동일하면 결과가 동일하다.
//   : 순수 함수


/*
fun fib(k: Int): Long = when (k) {
    0 -> 1
    1 -> 1
    else -> {
        if (map[k] != null)
            map[k]!!
        else {
            val result = fib(k - 1) + fib(k - 2)
            map[k] = result
            result
        }
    }
}
*/

/*
val map = mutableMapOf<Int, Long>()
fun fib(k: Int): Long = map.getOrPut(k) {
    when (k) {
        0 -> 1
        1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}
*/

fun fib(k: Int): Long = when (k) {
    0 -> 1
    1 -> 1
    else -> fib(k - 1) + fib(k - 2)
}

class Helper<T, R>(private val function: (T) -> R) : (T) -> R {
    val map = mutableMapOf<T, R>()
    override fun invoke(k: T): R = map.getOrPut(k) {
        function(k)
    }
}

fun <A, R> ((A) -> R).memoized(): (A) -> R = Helper(this)

fun main() {
    val f = ::fib.memoized()
    println(f(50))
}