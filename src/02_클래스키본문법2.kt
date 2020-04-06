package ex2_2
// 클래스 기본 문법 2

class Car(val name: String, val speed: Int) {
    // 초기화 블록 - 객체가 생성되는 시점에 호출되는 구문
    //           - 여러 개의 생성자가 있을 경우, 공통 로직을 캡슐화할 수 있다.
    init {
        println("Car 객체 생성")
    }

    fun go() {
        // name + "가 " + speed + "km로 이동"
        // String.format("%s가 %dkm로 이동", name, speed)
        
        // - 문자열 보간법(Template String)
        println("${name}가 ${speed}km로 이동")
    }

//    fun go(address: String) {
//
//    }

    // 기본 파라미터 값을 지정할 수 있습니다.
    // => 불필요한 함수 오버로딩을 제거할 수 있습니다.
    fun go(address: String, speed: Int = 42) {

    }
}

fun main() {
    val car = Car("BMW", 150)

    val a = "Suwon"
    val s = 120

    // 인자가 어떤 파라미터로 전달될지 지정하는 것이 좋다.
    // => 파라미터 지정 호출 문법이 존재합니다.
    car.go()
    car.go("aaa")
    car.go(speed = s, address = a)
}




