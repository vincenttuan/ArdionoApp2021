package com.study.ardionoapp.study1

// 抽象類別
// 紅茶 10, 奶茶 25, 奶綠茶 30

abstract class Tea(val name: String, val price: Int) {
    fun printTeaInfo() {
        println("$name $$price")
    }
    abstract fun printIngredientsOfDrink() // 飲料成分
}

class RedTea(name: String, price: Int): Tea(name, price) {
    override fun printIngredientsOfDrink() {
        println("紅茶 + 水")
    }
}

class MilkTea(name: String, price: Int): Tea(name, price) {
    override fun printIngredientsOfDrink() {
        println("茶 + 牛奶 + 水")
    }
}

fun main() {
    val redTea = RedTea("錫蘭紅茶", 10)
    val milkTea = MilkTea("厚奶茶", 25)
    val milkGeenTea = MilkTea("奶綠茶", 30)
    val tea = listOf<Tea>(redTea, milkTea, milkGeenTea)
    tea.forEach { it.printIngredientsOfDrink() }
    tea.forEach { it.printTeaInfo() }
    tea.forEach { println(it.name) }
    tea.forEach { println(it.price) }
    // 回家作業
    // 請問平均價格 ?
}