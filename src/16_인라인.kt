import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

// inline fun foo() {}

// 16_인라인
// : 람다 파라미터를 가지고 있는 함수에서 인라인 최적화를 허용한다.
// => 불필요한 호출의 코드를 제거하고, 메소드 안으로 복사 붙여넣기 된다.
inline fun<T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}


class IncThread(private val lock: Lock) : Thread() {
    // IncThread 객체가 공유하는 변수
    companion object {
        var n = 0
    }

    // * 코틀린에는 자바에서 사용하던 동기화의 방법이 제공되지 않습니다.
    //  - 동기화 메소드 / 동기화 블록
    override fun run() {
        for (e in 1..10000000) {
            withLock(lock) {
                ++n
            }
        }

        // for (int i = 0 ; i < 1000000; ++i) {}
        // Range: 1..1000000
        /*
        for (e in 1..10000000) {
            lock.lock()
            ++n                // 예외가 발생하면, unlock이 수행되지 않는다.
            lock.unlock()
        }
        */
    }
}

fun main() {
    val lock = ReentrantLock()

    val t1 = IncThread(lock)
    val t2 = IncThread(lock)

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println("value: ${IncThread.n}")
}