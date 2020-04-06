// 07_데이터클래스
package ex07

import java.util.*

// DTO, DAO, VO
//  - toString, equals / hashCode
// data class User2() // 프로퍼티가 한개도 존재하지 않는 data class는 없다.

data class User(val name: String, val age: Int) {
//    operator fun component1(): String {
//        return name
//    }
//    operator fun component2(): Int {
//        return age
//    }

    operator fun component3(): String {
        return "Suwon"
    }
}

fun main() {
    val u1 = User("Tom", 42)
    val u2 = User("Tom", 42)

    // 1. toString()
    println(u1)
    // 2. equals / hashCode
    println(u1 == u2)

    // 3. clone - 잘못 설계된 대표적인 함수
    // => copy를 제공합니다.
    val u3 = u1.copy(name = "Alice", age = 30)
    println(u3)

    val (name, age) = u3;

    // 4. 구조 분해 선언
    val users = listOf(u1, u2, u3)
    // 연산자 오버로딩
    for ((name, age, address) in users) {
        println("$name - $age")
    }

//    for (e in users) {
//        println("${e.name} - ${e.age}")
//    }

}

/*
class User(val name: String, val age: Int) {
    override fun toString(): String {
        return "User(name=$name,age=$age)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        // Smartcast가 발생하지 않기 때문에, 캐스팅이 필요합니다.
         if (javaClass != other?.javaClass) return false
         other as User

        // if (other !is User) return false

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }
}
*/