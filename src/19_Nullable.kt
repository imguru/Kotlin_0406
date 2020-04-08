// 19_Nullable
//  => Null 오류를 최소화할 수 있는 문법
//  => Optional

fun getName(): String? {
    return null
}

// * 외부에서 접근할 수 있는 var 변수에 대해서는 스마트 캐스트가
//   동작하지 않는다.
var n: Int? = null
fun main() {
    // 1. 일반 타입에 null을 사용하는 것은 불가능합니다.
    // let
    n?.let { n ->
        var result: Int = n
    }

    /*
    val n = n
    if (n == null) {
        return
    }

    // ...
    */

    /*
    if (n != null) {
        var result: Int = n
    }
    */

    // 2. 강제 참조 연산 - !!
    // Int? -> Int 에 대입은 허용 X
    // Int  -> Int? 에 대입은 허용 된다.
    // 주의해야 한다.
    //  : 널 일 경우 예외가 발생한다.

    var result: Int = n!!

    // 3. 널 인 경우 다른 값을 지정하고 싶다.
    //  => 삼항 연산자
    //  => 코틀린은 삼항 연산자가 제공되지 않습니다.
    var a = n ?: 42

    val n = n ?: return
}






