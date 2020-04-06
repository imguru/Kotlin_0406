package ex3

// 1) 기본 파라미터 값 지정은 자바에서는 존재하지 않는 기능입니다.
// 2) 해당 기능을 자바에서 온전히 사용하기 위해서는 오버로딩을 통해
//    바이트 코드가 생성되어야 합니다.
//     => @JvmOverloads

class User(val name: String, val age: Int, val address: String) {
    // : this(name, age, "") => 위임 생성자
    constructor(name: String, age: Int) : this(name, age, "")
    constructor(name: String) : this(name, 0, "")
}

/*
class User @JvmOverloads constructor(val name: String,
                                     val age: Int = 42,
                                     val address: String = "Suwon")
*/