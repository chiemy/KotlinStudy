package com.hotbody.kotlindemo

import java.lang.Integer.parseInt

/**
 * Created: chiemy
 * Date: 17/6/9
 * Description: 控制流
 */

fun testIf() {
    val a = 1
    val b = 2
    println("max value = ${getMaxWithTraditionalIf(a, b)}")
    println("max value = ${getMaxWithSimpleIf(a, b)}")
    getMaxIfBlock(a, b)
}

fun getMaxWithTraditionalIf(a: Int, b: Int): Int {
    // 传统用法
    var max = a
    if (a < b)
        max = b
    return max
}

fun getMaxWithSimpleIf(a: Int, b: Int): Int {
    // NOTE: if是一个表达式，有返回值
    return if (a > b) a else b
}

fun getMaxIfBlock(a: Int, b: Int): Int {
    // NOTE: if的分支可以是代码块，最后的表达式作为该块的值
    val max = if (a > b) {
        println("max value = $a")
        a
    } else {
        println("max value = $b")
        b
    }
    return max
}

fun testWhen() {
    // NOTE:  when 既可以被当做表达式使用也可以被当做语句使用。像 if 一样，每一个分支可以是一个代码块，它的值 是块中最后的表达式的值。
    whenAsStatement()
    whenAsExpression()
    expressionAsCondition()
    checkInRangeOrNot()
    checkIsTypeOrNot()
}

// 当做语句使用
fun whenAsStatement() {
    val x = getRandomInt(10)
    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> {
            println("x is neither 1 nor 2")
        }
    }
}

// NOTE: when 若作为表达式，符合条件的分支的值就是整个表达式的值，且必须有 else 分支，除非编译器能够检测出所有的可能情况都已经覆盖了
fun whenAsExpression() {
    val x = getRandomInt(10)
    val out = when (x) {
        // NOTE: 如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起, 用逗号分隔
        1, 2 -> "x == $x"
        else -> {
            "x is neither 1 nor 2"
        }
    }
    println(out)
}
// NOTE: 我们可以用任意表达式（而不只是常量）作为分支条件
fun expressionAsCondition() {
    val x = getRandomInt(10)
    when (x) {
        parseInt("1") -> println("s encodes x")
        else -> println("s does not encode x")
    }
}
// NOTE: 我们也可以检测一个值在（in）或者不在（!in）一个区间或者集合中：
fun checkInRangeOrNot() {
    val x = getRandomInt(30)
    when (x) {
        in 1..10 -> println("x is in the range")
        !in 10..20 -> println("x is outside the range")
        else -> println("none of the above")
    }
}

fun checkIsTypeOrNot() {
    val string = "Hello world"
    val startWithHello = startWith(string, "Hello")
    if (startWithHello) {
        println("$string is start with hello")
    } else {
        println("$string is not start with hello")
    }
}

fun getRandomInt(max: Int): Int {
    return (Math.random() * max).toInt()
}

fun startWith(any: Any?, prefix: String): Boolean {
    return when(any) {
        is String -> {
            any.startsWith(prefix, true)
        }
        else -> false
    }
}