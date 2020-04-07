package ex09_2

import kotlin.reflect.KProperty

// 09_위임2
//  클래스 위임도 할 수 있지만,
//  프로퍼티에 대한 동작(getter + setter)에 대해서 위임을 할 수 있다.
/*
class SampleDelegate {
    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        return "$thisRef - ${property.name}"
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("$thisRef - ${property.name} to $value")
    }
}
*/
interface Observer<T> {
    fun onValueChanged(old: T, new: T)
}

class Observable<T>(var value: T, val listener: Observer<T>? = null) {
    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val old = this.value
        this.value = value
        listener?.onValueChanged(old, value)
    }
}

class User {
    var name: String by Observable("", object: Observer<String> {
        override fun onValueChanged(old: String, new: String) {
            println("Name - $old -> $new")
        }
    })

    var address: Int by Observable(42, object : Observer<Int> {
        override fun onValueChanged(old: Int, new: Int) {
            println("Address - $old -> $new")
        }

    })
}

fun main() {
    val user = User()
    println(user.name)
    user.name = "Tom"
    user.name = "Bob"

    user.address = 30
}