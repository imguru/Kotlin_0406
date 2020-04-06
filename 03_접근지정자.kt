package ex3
// 03_접근지정자
// Java: private - default package - protected - public
// Kotlin: private - internal - protected - public
//                   같은 모듈   자식 클래스

// 1) public class가 기본입니다.
class Foo

// 2) 하나의 파일에 여러개의 public class를 둘 수 있습니다.
class Goo


// IntelliJ / Android Studio
//  Project
//   - Module (Library)
//   - Module (GUI)

// 3) 아래 클래스는 같은 [모듈]안에서만 접근이 가능합니다.
internal class Hoo

// 4) private class는 같은 [파일]안에서만 접근이 가능합니다.
private class Koo

// * 위의 규칙은 전역 함수에서도 동일하게 적용됩니다.
public fun foo() {}
internal fun goo() {}  // 모듈
private fun hoo() {}   // 파일

// class AAA {
//    [    ] String name;
// }


// ex3.User





