package ex08_2

// 익명 객체를 만들 때 사용한다.
// 3. Anonymous object - 익명 객체
interface MouseAdapter {
    fun mouseClicked()
    fun mouseEntered()
}

class Window {
    // 1. lateinit var - 객체 생성 이후에 초기화하겠다.
    //    주의: 초기화하지 않을 경우 예외 발생
    var mouseListener: MouseAdapter? = null

    fun click() {
        /*
        if (mouseListener != null)
            mouseListener.mouseClicked()
        */
        // null check와 함수의 실행을 원자적으로 수행하는 방법
        // mouseListener?.mouseClicked()

        // mouseListener!!.mouseClicked()
        // 강제 접근 - null 일 경우 예외 발생
    }
}

fun main() {
    val window = Window()

    // Lambda - Java vs Kotlin
    window.mouseListener = object : MouseAdapter {
        override fun mouseClicked() {
            println("Mouse clicked")
        }
        override fun mouseEntered() {
            println("Mouse entered")
        }
    }

    window.click()
}




