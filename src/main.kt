
// Array<String>? == String[]
// Nullable

fun main(args: Array<String>) {
    println("Hello, Kotlin")
}

// ❯ kotlinc main.kt
// -> MainKt.class 파일이 생성

// > java MainKt
// -> Hello, Kotlin : main()
// -> kotlin.jvm.internal.Intrinsics.checkParameterNotNull 함수 호출로 인한 에러
//  => java를 통해 실행할 경우, 코틀린 라이브러리에 대한 로드가 존재하지 않는다.

// ❯ kotlin MainKt
// Hello, Kotlin

// ❯ kotlinc main.kt -include-runtime -d Main.jar
// ❯ java -jar Main.jar

// REPL(Read-Eval-Print-Loop)
// - kotlinc-jvm









