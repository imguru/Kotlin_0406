package ex4

// * 프로퍼티 종류
// 1) Field + 접근자 메소드 = 프로퍼티
// 2) Field X + 접근자 메소드 = Backing Field가 존재하지 않는 프로퍼티

// * 프로퍼티 vs 메소드
//  1) 복잡한 코드는 메소드를 사용한다.
//  2) 시간이 오래 걸리는 작업은 메소드를 사용한다.
//  3) 프로퍼티의 값을 읽는 것은 부수 효과의 원인이 되면 안된다.
//     getter 안에서 객체 내부의 값을 변경하는 행위는 좋지 않다.
//  4) 프로퍼티 안에서 절대 예외는 발생하면 안된다.
//  5) 타입을 다른 타입으로 변환하고자 한다.
//      toXXXX() 계열의 메소드를 만들자!
//  6) 객체를 복제하는 것은 메소드로 만드는 것이 좋다.

// * 프로퍼티 - 접근자 메소드
//  val - getter 생성
//  var - getter / setter 모두 생성
class User(var firstName: String, var lastName: String) {
    val age: Int
        get() {
            // 계산 수행 후 값을 반환
            return 42
        }

    var fullName: String
        get() {
            return "$firstName, $lastName"
        }
        set(value) {
            // 아래 함수는 자바의 함수가 아니라, 코틀린이 제공하는 함수 입니다.
            val arr = value.split(" ")
            firstName = arr[0]
            lastName = arr[1]
        }
}

fun main() {
    val user = User("Gildong", "Hong")
    println(user.fullName)

    user.fullName = "Soonsin Lee"
    println(user.firstName)
    println(user.lastName)

    // user.setName("Bob")
}



/*
class User {
    var name: String
        get() {
            return "Alice"
        }
        set(value) {
            // name = value
            // 주의 해야 합니다. 이름을 통해 접근하는 것은 접근자 메소드가 호출됩니다.

             println("$field -> $value")
             field = value
        }
    // Getter / Setter = Accessor Method(접근자 메소드)
    // 정리: 필드와 접근자 메소드를 자동으로 생성하는 문법
    //   => C#, ObjC, Swift, Kotlin

    constructor(name: String) {
        this.name = name
    }
}


fun main() {
    val user = User("Tom")
    user.name = "Bob"   // setName("Bob")
    println(user.name)  // getName()


    // user.setName("Bob")
}
*/
