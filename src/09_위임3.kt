// 09_위임3.kt
// 코틀린이 이미 제공하는 다양한 표준 델리게이트 객체가 있습니다.
package ex09_3

import kotlin.properties.Delegates

// 1. lazy
/*
class User {
    // lateinit var name: Double
    // 1. var에서만 사용할 수 있다.
    // 2. getter / setter 재정의할 수 없다.
    // 3. Primitive type(Int, Long, Float, Double)에서는 불가능하다.

    // 초기화 되는 시점을 객체가 생성되는 시점이 아니라
    // 처음 접근되는 시점에 초기화하고 싶다.
    // => Lazy Initialization(지연 초기화)
    // => 블록의 결과는 마지막 표현식의 결과로 결정된다.
    val name: String by lazy {
        println("name 초기화")
        "B"
    }
    // 1) val에 대해서만 사용 가능하다.
    // 2) 동시에 접근할 경우, 스레드 안전하게 초기화되는 것을 보장한다.
    
    init {
        println("User 객체 생성")
    }
}

fun main() {
    val user = User()
    println("User 객체 생성 완료")
    println(user.name)
}
*/

/*
// 2. KVO: Key-Value Observation
// : 프로퍼티 값의 변경에 대해서 수행되는 로직을 캡슐화 할 수 있습니다.
class TextView {
    var text: String = ""
        set(value) {
            println("TextView - $value")
            field = value
        }
}

class Activity {
    // 아래의 블록은 값의 변경이 발생하는 시점에 수행되는 블록입니다.
    // => 사용하지 않는 인자는 _로 대체하면 됩니다.
    var name by Delegates.observable("Tom") { _, oldValue, newValue ->
        // 값이 변경될 때마다 자동적으로 TextView의 내용이 변경될 것 입니다.
        nameTextView.text = newValue
        println("$oldValue -> $newValue")
    }

    val nameTextView = TextView()
}

fun main() {
    val activity = Activity()
    activity.name = "Bob"
}
*/

/*
// 3. KVC - Key-Value Coding
//  Key-Value기반의 데이터(JSON - Map<String, Any>)를 기반으로
//  프로퍼티의 값을 초기화하고 싶다.
class User(json: Map<String, Any>) {
    val name: String by json
    val age: Int by json

    override fun toString(): String {
        return "User(name=$name, age=$age)"
    }

    /*
    // 아래의 코드는 프로퍼티가 추가될 때마다 수정되어야 한다.
    init {
        name = json["name"] as String
        age = json["age"] as Int
    }
    */
}

class Response(json: Map<String, Any>) {
    val user: User by json
}


fun main() {
    val json = mapOf(
            "name" to "Tom",
            "age" to 42
    )

    val user = User(json)
    println(user)

    val json2 = mapOf(
            "user" to user
    )

    val response = Response(json2)
    println(response.user)
}
*/

// 4. vetoable: validation 조건에 부합되지 않으면 값이 변경되지 않도록 하고 싶다.
class User {
    var name: String by Delegates.vetoable("Gildong") { _, old, new ->
        new.length >= 5
    }
}

fun main() {
    val user = User()
    user.name = "xxxxx"
    println(user.name)

}


