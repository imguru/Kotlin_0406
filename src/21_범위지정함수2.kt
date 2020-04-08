// 21_범위지정함수2
package ex21

import com.lge.jv.Program6
import java.io.DataOutputStream
import java.io.FileOutputStream

// let, apply, with, also, run
// let: nullable 체크 목적으로 사용해야 합니다.
fun foo(s: Int?) {
    s?.let {
        println(s)
    }

    StringBuilder()
            .append(10)
            .append('a')
            .append("hello")
            .toString()


    val t = StringBuilder().apply {
        append(123)
        // ...
    }.toString()
    // .build()

}


// withLock: 동기화
//  : 자바의 동기화 블록의 역활

// Try with Resources
//  : 리소스의 해제에 대한 처리
// => use
//  - AutoClosable 인터페이스를 따르는 자원에 대해서 close를 자동으로
//    호출해준다.
/*
fun main() {
    val fos = FileOutputStream("a.txt")
    val dos = DataOutputStream(fos)

    // String  -> @NotNull
    // String? -> @Nullable
    // String! -> Java's String

    Program6.foo("hello")

    fos.use {
        dos.use {
            dos.writeBytes("Kotlin")
        }
    }
}
*/


// Android
interface OnClickListener {
    fun onClick()
}

class ButtonKt {
    var onClick: (() -> Unit)? = null
    fun click() {
        onClick?.invoke()
    }
}

class Button {
    var listener: OnClickListener? = null
    fun click() {
        listener?.onClick()
    }
}

fun main() {
    val button = Button()
    // button.setOnClickListener
    button.listener = object: OnClickListener {
        override fun onClick() {
            // ...
        }
    }

    val button2 = ButtonKt()
    button2.onClick = {
        // ...
    }
}












