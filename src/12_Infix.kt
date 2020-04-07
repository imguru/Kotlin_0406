// 12_Infix

// 인자가 하나뿐인 일반 함수나 메소드에 중위 호출을 적용할 수 있습니다.
infix fun<T, F> T.pair(last: F) = Pair(this, last)

fun main() {
//    val pair1 = Pair("name", "Tom")
//    val pair2 = Pair("age", 42)

//    val pair1 = pair("name", "Tom")
//    val pair2 = pair("age", 42)

    val pair1 = "name".pair("Tom")
    val pair2 = "name" pair "Tom"

    // Pair는 구조 분해 선언 문법을 지원합니다.
    val (name, age) = pair1

    val map = mapOf(
            "name" to "Tom",
            "age" to 42
    )
}