package com.study.ardionoapp.study1

fun main() {
    // 集合(高級陣列): Set, List, Map
    // Set: 元素不會重複, ex: [1, 3, 5, 7] or [甲, 乙, 丙]
    // List: 元素可重複, ex: [1, 3, 3, 7] or [100, 90, 90]
    // Map: 每個元素都是 key/vslue 的組合, ex: [甲: 100, 乙: 90, 丙: 90]
    //                 key: [甲, 乙, 丙], value: [100, 90, 90]
    val set = setOf<Int>(1, 5, 2, 7, 6, 3, 1)
    //set.add(9) // 不可增加元素
    println(set) // [1, 5, 2, 7, 6, 3]
    val set2 = mutableSetOf<Int>(1, 5, 2, 7, 6, 3, 1)
    set2.add(9) // 可增加元素 (mutable 表示該集合元素可以新增修改刪除)
    println(set2)
}
