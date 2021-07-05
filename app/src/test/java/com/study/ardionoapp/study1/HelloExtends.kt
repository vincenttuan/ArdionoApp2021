package com.study.ardionoapp.study1

// 抽象類別
abstract class Human(val name: String) {
    abstract fun eat()
}

class Man(name: String): Human(name) {
    override fun eat() {
        println("$name 大口吃飯")
    }
}

class Woman(name: String) : Human(name) {
    override fun eat() {
        println("$name 小口吃飯")
    }
}

fun main() {
    val m = Man("男人")
    val f = Woman("女人")
    val list = listOf<Human>(m, m, m, f, f, m)
    list.forEach { it.eat() }
}
