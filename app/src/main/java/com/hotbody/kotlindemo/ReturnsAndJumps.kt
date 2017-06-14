package com.hotbody.kotlindemo

/**
 * Created: chiemy
 * Date: 17/6/14
 * Description: 返回和跳转 https://www.kotlincn.net/docs/reference/returns.html
 */
fun testReturnsAndJumps() {
    // NOTE: 标签的定义与引用
    // 定义一个标签 标识名称 + @，例如 abc@
    // 引用一个标签 @ + 标签名称 如 @abc

    breakNormal()

    breakLabel()

    continueNormal()

    continueLabel()

    val list = listOf(1, 0, 1)
    // 输出 1
    returnNormal(list)
    println("")
    // 输出 1 1
    returnLabel(list)
    // 输出 1 1
    anonymousFunReplaceLambda(list)
    // 输出 [number 1, zero, number 1]
    println(convert(list))
}

// NOTE: break
// break 结束内层循环
private fun breakNormal() {
    for (i in 1..3) {
        for (j in 1..3) {
            if (i * j % 4 == 0) break
            print("${i * j} ")
        }
    }
    println("")
    // 输出 1 2 3 2 3 6 9
}

// 给外层循环加标签，break指定该标签，会跳转到标签指定的循环之后（即结束所有循环）
private fun breakLabel() {
    outloop@ for (i in 1..3) {
        for (j in 1..3) {
            if (i * j % 4 == 0) break@outloop
            print("${i * j} ")
        }
    }
    println("")
    // 输出 1 2 3 2
}

// NOTE: continue
// continue 进入下一次内层循环
private fun continueNormal() {
    for (i in 1..3) {
        for (j in 1..3) {
            if (i * j % 4 == 0) continue
            print("${i * j} ")
        }
    }
    println("")
    // 输出 1 2 3 2 6 3 6 9
}

// 给外层循环加标签，continue 指定该标签，会进入外层循环的下一次迭代
private fun continueLabel() {
    outloop@ for (i in 1..3) {
        for (j in 1..3) {
            if (i * j % 4 == 0) continue@outloop
            print("${i * j} ")
        }
    }
    println("")
    // 输出 1 2 3 2 3 6 9
}

// NOTE: return
// 表达式从最直接包围它的函数即 returnNormal 中返回。（forEach {} 内是 lambda 表达式）
fun returnNormal(ints: List<Int>) {
    ints.forEach {
        // 满足跳转后直接结束函数
        if (it == 0) return
        print("$it ")
    }
    println("no value = 0")
}

// 从 lambda 表达式中返回
fun returnLabel(ints: List<Int>) {
    ints.forEach {
        // NOTE:  隐式标签
        if (it == 0) return@forEach
        print("$it ")
    }
    println("")
}

// NOTE: 匿名函数
// 用一个匿名函数替代 lambda 表达式。 匿名函数内部的 return 语句将从该匿名函数自身返回
fun anonymousFunReplaceLambda(ints: List<Int>) {
    ints.forEach(fun(value: Int) {
        if (value == 0) return
        print("$value ")
    })
    println("")
}

// 从标签返回值
fun convert(ints: List<Int>): List<String> {
    val result = ints.map f@{
        if (it == 0) return@f "zero" // 从标签 f 返回 "zero"
        "number $it" // 从 lambda 表达式返回
    }
    return result
}
