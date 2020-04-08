// Android
/*
 Intent intent = new Intent(this, MainActivity.class);
 startActivity(intent);
*/

open class Context {
    fun startActivity(context: Context, activityClass: Class<out Activity>) {
        val new = activityClass.newInstance()
        new.create()
    }
}

open class Activity : Context() {
    open fun onCreate() {
        println("${javaClass.simpleName} onCreate")
    }

    fun create() {
        onCreate()
    }
}

// Generic
// 1. JVM의 제네릭은 타입 소거(Type erasure)를 사용해 구현된다.
// => 실행 시간에 제네릭 클래스의 타입 인자 정보는 제거된다.

inline fun <reified T : Activity> Context.startActivity() {
    startActivity(this, T::class.java)
}

class MainActivity : Activity() {
    override fun onCreate() {
        super.onCreate()

        // startActivity(this, DetailActivity::class.java)
        startActivity<DetailActivity>()
    }
}

class DetailActivity : Activity() {
    override fun onCreate() {
        super.onCreate()
    }
}

/*
fun main() {
    val activity = MainActivity()
    activity.create()
}
*/

// 자바의 제네릭은 컴파일러가 타입을 체크하는 목적으로 사용된다.
inline fun<reified T> isA(value: Any) = value is T

fun main() {
    val list1: List<String> = listOf("a", "b")
    val list2: List<Int> = listOf(1, 2, 3)

    println(isA<String>("aaa")) // value is String
}








