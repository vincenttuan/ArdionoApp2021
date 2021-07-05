package com.study.ardionoapp.study1

data class Person(val name: String, val age: Int)

fun main() {
    val p1 = Person("John", 18)
    val p2 = Person("Mary", 19)
    val p3 = Person("Helen", 20)
    println(p1);println(p2);println(p3)
    println("----------------------------")
    // 請問年齡最大的是誰 ?
    // 提示：先將 p1, p2, p3 放到 list 集合中在進行運算
    val people = listOf<Person>(p1, p2, p3)
    val person = people.maxByOrNull { it.age }
    if(person != null) {
        println(person.name)
    }
    println(person?.name)  // 若 person = null, 則印出 null
    println(person!!.name) // 忽略 null 值的警告, 最好要配合 try-catch
    println("----------------------------")
    // 請問平均年齡幾歲 ?
    var sum = 0
    people.forEach { sum+=it.age }
    var avg = sum / people.size
    println(avg)
    println("----------------------------")
    /*
    * [Person(name=John, age=18), Person(name=Mary, age=19), Person(name=Helen, age=20)]
    * mapToInt 轉換為 Int 元素
    * [18, 19, 20]
    * */
    var avg2 = people.stream().mapToInt { it.age }.average().asDouble
    println(avg2)
    println("----------------------------")
    val stat = people.stream().mapToInt { it.age }.summaryStatistics()
    println("統計資訊：$stat")
    println("平均：${stat.average}")
}