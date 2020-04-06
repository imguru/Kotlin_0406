package ex2

import java.util.*

// 코틀린에서는 패키지의 이름에 해당하는 디렉토리에 파일이 존재하지 않아도 됩니다.
// 하지만 기존 자바 방식처럼 사용하는 것이 권장됩니다.

// * public class를 별도의 파일에 분리할 필요가 없습니다.
/* Version 1.
class User {
    private val name: String
    private val age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}
*/

// Version 2
/*
class User constructor(private val name: String,
                       private val age: Int)
*/

// Object -> Any 이름으로 변경되었습니다.
// T  => null이 존재하지 않는 타입
// T? => null가능성이 있는 타입(Nullable Type)

// Primary Constructor(주 생성자)
// VO / DTO / DO
/*
class User(private val name: String,
           private val age: Int) {

    // 1) Object -> Any
    // 2) Nullable Type
    // 3) Smart Cast
    // 4) @Override -> override
    // * equals를 제공하면 반드시 hashCode도 제공해야 한다.

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }

    override fun equals(other: Any?): Boolean {
        if (other === null)
            return false

        // Any? -> Any
        if (other === this)
            return true

        if (other !is User)
            return false
        // Any -> User
        // Smart Cast 문법
        // = 컴파일러가 코드를 파악해서, 타입의 추론을 진행합니다.

        return name == other.name && age == other.age
    }
}
*/

// hashCode / equals가 자동으로 제공됩니다.
data class User(private val name: String,
                private val age: Int)

fun foo(n: Int) {
}

/*
fun main() {
    var n: Int? = 42
    // n = null

    if (n != null) {
        n += 10
    }

    // User user = new User("Tom", 42);
    val user1 = User("Tom", 42)
    val user2 = User("Tom", 42)
    // val user2 = user1

    // 동등성 판단       Java           Kotlin
    // 1) 객체 동등성 -  equals          ==
    // 2) 참조 동등성 -  ==              ===

    if (user1 === user2) {
        println("같은 참조를 가지고 있습니다.")
    }

    // Null Check + equals = Objects.equals
    if (user1 == user2) {
        println("동일한 값을 가지고 있습니다.")
    }
}
*/
// User.java
/*
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
*/

// 기본 연산자: >>>(논리 쉬프트), >>(산술 쉬프트), <<, |, &
fun main() {
    val n = 0b11110000
    println(n)

    println(n shr 1)  // n >> 1
    println(n shl 1)  // n << 1
    println(n ushr 1) // n >>> 1

    println(n and 0b11110000)
    println(n or 0b111000)
    println(n xor 0b111000)
    println(n.inv())
}





