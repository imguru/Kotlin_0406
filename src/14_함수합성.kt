package ex14
// 14_함수합성
// Function Composition

// f(x: X) -> y: Y
// g(y: Y) -> z: Z

//   f(x) * g(y) = g(f(x))
// => h(x) -> z

// f: (String) -> Int
// g: (Any) -> Int
// h: (String) -> Int


//fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = { x ->
//    val y = f(x)
//    val z = g(y)
//    z
//}

//infix fun <X, Y, Z> ((X) -> Y).compose(g: (Y) -> Z): (X) -> Z = { x ->
//    val y = this(x)
//    val z = g(y)
//    z
//}

infix fun <X, Y, Z> ((X) -> Y).compose(g: (Y) -> Z): (X) -> Z = { x ->
    g(this(x))
}

fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    // val fog = compose(f, g)
    // val fog = f.compose(g)
    val fog = f compose g

    val r1 = fog("hello")
    val r2 = fog("olleh")

    println(r1)
    println(r2)
}









