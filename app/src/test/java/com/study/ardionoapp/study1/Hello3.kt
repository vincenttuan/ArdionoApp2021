package com.study.ardionoapp.study1

fun main() {
    val r = 10.5
    // 圓面積
    var area = r * r * 3.14
    println(area)
    area = r * r * Math.PI
    println(area)
    area = Math.pow(r, 2.0) * Math.PI
    println(area)
    println("%f".format(area))
    println("%.2f".format(area))
    println("圓面積 = %.2f".format(area))
    println("半徑 = %.1f, 圓面積 = %.2f".format(r, area))
    println("半徑 = $r, 圓面積 = $area")
    // 球體積 公式: 4/3 * (pi * r的3次方)
    // 請印出 半徑 = 10.5, 球體積 = ....
}