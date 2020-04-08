package ex20

import java.io.File
import kotlin.streams.toList

// 20_코틀린 표준 라이브러리
//   => 스트림 함수 / 시퀀스 함수
//     (Java 8)    (Kotlin)

//   스트림 API
//   => 컬렉션에 연산을 수행하는 다양한 함수의 집합
//   => 마시멜로우 이상(Android 6 이상)

//   시퀀스 API
//   => 자바 6에서 동작하는 코틀린에서 제공되기 때문에,
//      문제없이 사용할 수 있습니다.
//
/*
fun main() {
    // 1. map(transform)
    // : 각각의 요소에 변환을 수행한다.

    // 2. filter
    // : 컬렉션에서 필요하지 않는 데이터는 걸러낸다.

    // 3. mapNotNull
    // : 변환을 수행하고, null인 결과는 걸러낸다.

    val cities = listOf("seoul", "suwon", "busan")

    // cities: List<String>
    //    => Map<Char, List<String>>
    // 5. groupBy
    val r4 = cities.groupBy { city ->
        city[0]
    }

    r4.forEach { e ->
        println(e)
    }

    val r1 = cities.map { e ->
        if (e.startsWith("b"))
            e.toUpperCase()
        else
            null
    }.filter {
        it != null
    }

    val r2 = cities.mapNotNull { e ->
        if (e.startsWith("b"))
            e.toUpperCase()
        else
            null
    }


    r1.forEach {
        println(it)
    }

   

    // 10 -> 1..10
    // 20 -> 1..20
    // 30 -> 1..30
    // 4. flatMap
    // : 연산의 결과가 컬렉션인 경우, 1차원으로 변경해서 변환한다.
    val list2 = listOf(10, 20, 30)
    val r3 = list2.flatMap { n: Int ->
        1..n
    }

    r3.forEach {
        println(it)
    }
}
*/

// distinct: 중복된 항목을 걸러낸 결과를 반환합니다.
// first / firstOrNull
// last / lastOrNull
// take: 컬렉션에서 원하는 요소를 추출합니다.
// drop: 원하지 않는 요소를 제거합니다.
/*
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 5, 5)
    // val e = list.get(0) // list[0]
    // 위처럼 사용하면, 리스트가 비어있을 경우, 예외가 발생합니다.

    // * 예외를 통해서 실패를 전달할수도 있지만, 연산의 실패를 Nullable을
    //   통해서 사용할 경우, 더 편리합니다.
//    list.take(5).forEach {
//        println(it)
//    }

    list.dropWhile {
        it < 5
    }.forEach {
        println(it)
    }



    list.lastOrNull { e ->
        e == 8
    }?.let { e ->
        println(e)
    }

    try {
        list.last { e ->
            e == 8
        }.let { e ->
            println(e)
        }
    } catch (e: NoSuchElementException) {
        // e.printStackTrace()
    }

    // val unique = list.distinct()
    // println(unique)
    // 자바에서는 Set을 이용해서 구현하면 편리합니다.
}
*/

// zip: 두 개의 컬렉션을 조합하여 하나의 컬렉션으로 만들 수 있다.
/*
fun main() {
    val a = listOf("Korea", "China", "Japan", "US")
    val b = listOf("KR", "CN", "JP")

    // Pair, Triple
    a.zip(b).forEach { (country, code) ->
        println("$country($code)")
    }
}
*/

fun main() {
    val list = listOf(1, 2, 3, 4, 5)

    // Java 8
    // - Lambda + Stream API
    val result = list.stream().map {
        it * 10
    }


    /*
    val result = list.map {
        it * 10
    }
    */
    for (e in result) {
        println(e)
    }

    // println(result)
}


// 1. 조건 확인 함수
//   check
//   require

// => 성립 되지 않을 경우 예외가 발생한다.
//   requireActivity
//   checkActivity

fun foo(filename: String, message: String?) {
    val f = File(filename)
    check(f.exists())   // - IllegalStateException
    require(f.exists()) // - IllegalArgumentException
    /*
    if (!f.exists()) {
        throw IllegalStateException("파일이 존재하지 않습니다")
    }
    */
    checkNotNull(message)
    requireNotNull(message)
}

// error() / TODO()
// - 명시적인 종료 함수
/*
fun main() {
    // error("실행 불가능합니다")
    // TODO("hello")
}
*/






