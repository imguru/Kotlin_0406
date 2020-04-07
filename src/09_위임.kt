package ex09

// 09_위임
// 기존의 코드를 재사용하는 방법
//  1) 상속 X - 다형성을 목적으로 사용해야 한다.
//  2) 포함(위임)
interface UIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

open class Rectangle(
        val x1: Int,
        val x2: Int,
        val y1: Int,
        val y2: Int
) : UIElement {
    override fun getHeight(): Int {
        return y2 - y1
    }

    override fun getWidth(): Int {
        return x2 - x1
    }
}

// 상속
class Panel(x1: Int, x2: Int, y1: Int, y2: Int) : Rectangle(x1, x2, y1, y2)

// 포함(위임) - 의존성 주입
class Panel2(rectangle: UIElement) : UIElement by rectangle

/*
class Panel2(val rectangle: Rectangle) : UIElement {
    override fun getHeight(): Int {
        return rectangle.getHeight()
    }

    override fun getWidth(): Int {
        return rectangle.getWidth()
    }
}
*/

fun main() {
    // val panel = Panel(10, 20, 30, 40)
    val panel = Panel2(Rectangle(10, 20, 30, 40))

    println(panel.getWidth())
    println(panel.getHeight())
}



