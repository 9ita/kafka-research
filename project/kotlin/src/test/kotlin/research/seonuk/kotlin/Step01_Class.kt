package research.seonuk.kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Step01_Class {

    class Person {
        var name: String = ""
        var age: Int = 0

        fun displayInfo() {
            println("Name: $name, Age: $age")
        }
    }

    @Test
    fun test() {
        val person = Person()  // new 키워드 없이 객체 생성
        person.name = "John"
        person.age = 30
        person.displayInfo()
    }

}