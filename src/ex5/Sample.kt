package ex5

// Sample.kt -> SampleKt.class
fun foo() {
    println("foo")
}

fun goo(f: (Int) -> Boolean) {
    println("goo - ${f(42)}")
}
