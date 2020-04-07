// 08_object


package ex5

import java.io.File

// 1) object 선언 - Singleton을 만드는 문법입니다.
//  * 인자를 받는 생성자를 정의할 수 없습니다.
object Mouse {
    // 초기화 블록을 통해 객체의 초기화를 수행할 수 있습니다.
    init {
        println("Mouse object created!")
    }

    var name: String = "Mouse1"
}

// 활용 - 상속과 인터페이스를 구현하는 것이 가능하다.
object FileComparator : Comparator<File> {
    override fun compare(p0: File, p1: File): Int {
        return p0.path.compareTo(p1.path)
    }
}

class Person2(val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int {
            return o1.name.compareTo(o2.name)
        }
    }
}

/*
fun main() {
    val people = listOf(Person("Tom"), Person("Bob"))
    val r = people.sortedWith(Person.NameComparator)
    for (e in r) {
        println(e.name)
    }

    val files = listOf(File("/Z"), File("/A"))
    val result = files.sortedWith(FileComparator)
    for (e in result) {
        println(e.path)
    }


    val result2 = files.sortedWith(FileComparator)

    println("main()")
    println(Mouse.name)

    println("main() end")
}
*/

// 2. companion object = static field / method
class User2 {
    // static String sName = ""
    // static final String NAME = ""
    // companion object Hello {
    // 아래 같이 사용할 경우, Companion 이라는 이름을 통해 해당 프로퍼티나 메소드를 자바에서 접근 가능하다.
    companion object Hello {
        val sName: String = ""
        const val NAME: String = ""
        // val TAG: String = User::class.java.simpleName

        fun foo() {
            println("foo")
        }
    }
}


/*
// 2. companion object 활용
//  => 정적 팩토리 메소드(static factory method)
//    * 생성자 한계
//     - 생성자 오버로딩은 가독성에 문제가 있다.
//     - 생성자를 직접 호출 할 경우, 객체 생성의 정책을 변경할 수 없다.
class User {
    val nickname: String

    private constructor(email: String) {
        nickname = email.substringBefore("@")
    }

    private constructor(facebookId: Int) {
        nickname = "$facebookId@facebook"
    }

    companion object {
        fun newEmailUser(email: String): User {
            return User(email)
        }

        fun newFacebookUser(facebookId: Int): User {
            return User(facebookId)
        }
    }
}

fun main() {
    // val u1 = User("chansik.yun@gmail.com")
    // val u2 = User(1231223123)
    val u1 = User.newEmailUser("chansik.yun@gmail.com")
    val u2 = User.newFacebookUser(213123)

    // println(User.name)
    // println(User.foo())
}
*/

// companion object 활용 2
//  => 객체이기 때문에, 인터페이스를 구현하거나 상속이 가능하다.
//  => 마치 타입이 새로운 인터페이스나 기능을 구현할 수 있는 것처럼 보일 수 있다.
interface MapFactory<T> {
    fun fromMap(map: Map<String, Any>): T
}

data class Person(val name: String) {
    companion object : MapFactory<Person> {
        override fun fromMap(map: Map<String, Any>): Person {
            val name = map["name"] as String
            return Person(name)
        }
    }
}

fun <T> loadFromMap(factory: MapFactory<T>, map: Map<String, Any>): T {
    return factory.fromMap(map)
}

fun main() {
    val json: Map<String, Any> = mapOf(
            "name" to "Tom"  // Pair<String, String>("name", "Tom")
    )

    // Gson(Google JSON Seri / Dese) - Reflection

    // 자바에서 아래 기능을 구현하기 위해서는 Reflection이 반드시 필요합니다.
    // val person = Person.fromMap(json)
    val person = loadFromMap(Person, json)
    println(person)
}









