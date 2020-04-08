package ex21

// 21_범위지정함수

fun <T> w(ref: T, fn: T.() -> Unit) {

}

// 2. with
// with의 결과는 블록의 마지막 라인에 의해 결전된다
//fun alphabet(): String = with(StringBuilder()) {
//    for (letter in 'A'..'Z') {
//        append(letter)
//    }
//    toString()
//}

// 3. apply
// : Any.apply
//  모든 객체는 apply 함수를 가지고 있다
//  apply의 결과는 자기 자신이다.
fun alphabet1(): String = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
}.toString()

fun alphabet(): String = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
}




//fun alphabet(): String {
//    val result = StringBuilder()
//    for (letter in 'A'..'Z') {
//        result.append(letter)
//    }
//    return result.toString()
//}


// 1. let
/*
fun foo(): String? = ""

fun main() {
    val r = foo()
    r?.let {
        r.length
    }
}
*/