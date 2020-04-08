package ex17

// 코틀린
//  - 익명 함수
//  - 람다 표현식

// * 람다 표현식에서 return을 사용할 경우, 비지역 반환을 수행한다.
//   람다 표현식을 가지고 있는 함수에 대한 반환이 수행된다.
//   => for 루프를 통해 작성된 코드를 쉽게 함수 기반의 루프로 변경 가능하다.
// * 코틀린은 지역 반환의 기능을 제공하고 있습니다.
//   => 람다 블록에 대한 반환을 수행할 수 있습니다.

class Person(val name: String)

// 인라인이 지정되지 않은 함수에서는 비지역 반환이 허용되지 않습니다.
inline fun List<Person>.forEach2(action: (Person) -> Unit): Unit {
    for (e in this) action(e)
}

fun lookForAlice(people: List<Person>) {
    people.forEach2(fun(person){
        if (person.name == "Alice") {
            println("Found!")
            // return  // 지역 반환
            return@lookForAlice // 비지역 반환
        }
    })

    println("Alice is not Found")
}


fun lookForAlice2(people: List<Person>) {
    people.forEach2 hello@{ person ->
        if (person.name == "Alice") {
            println("Found!")
            // return@forEach2
            // return@hello
            return@lookForAlice2
        }
    }

    println("Alice is not Found")
}


/*
fun lookForAlice(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            return
        }
    }
    println("Alice is not Found")
}
*/


fun main() {
    val people = listOf(
            Person("Tom"),
            Person("Alice")
    )
    lookForAlice(people)
}