package com.hotbody.kotlindemo

/**
 * Created: chiemy
 * Date: 17/6/9
 * Description: Kotlin 基本类型
 */
fun basicTest() {
    testNumber()
    testArray()
    testString()
}

fun testNumber() {
    // NOTE: 17/6/9 字面常量
    // 十进制
    val int: Int = 1
    val long: Long = 1L
    // 十六进制
    val a: Int = 0x0f
    val b: Int = 0b00001011

    // NOTE: 17/6/9 存储方式
    val c: Int = 1
    val d: Int = 2
    println(c === d) // 打印 true
    // 可空引用会自动装箱
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA) // 打印 false

    // NOTE: 17/6/9 显式转换。不能隐式转换，只能显式转换
    val byte: Byte = 1
    // 错误
    // val e: Int = byte
    // 正确
    val e: Int = byte.toInt()

    // NOTE: 17/6/9 对于位运算，没有特殊字符来表示，而只可用中缀方式调用命名函数
    /**
    这是完整的位运算列表（只用于 Int 和 Long）：
    shl(bits) – 有符号左移 (Java’s <<)
    shr(bits) – 有符号右移 (Java’s >>)
    ushr(bits) – 无符号右移 (Java’s >>>)
    and(bits) – 位与
    or(bits) – 位或
    xor(bits) – 位异或
    inv() – 位非
     */
}

fun testArray() {
    // NOTE: 17/6/9 数组 Array，Kotlin 中数组是不协变的（invariant）
    // NOTE: 17/6/9 协变 https://www.zybuluo.com/zhanjindong/note/34147
    // 这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>
    // 创建数组 [1, 2, 3]
    val array = arrayOf(1, 2, 3)
    // 创建指定大小，元素为空的数组
    val arrayOfNulls = arrayOfNulls<Int>(3)
    arrayOfNulls.iterator().forEach { println(it) }
    // 接受数组大小和一个函数参数的工厂函数，用作参数的函数能够返回 给定索引的每个元素初始值
    // [0, 1, 4]
    val asc = Array(3, { index -> index * index })
    asc.iterator().forEach { println(it) }
    // [] 运算符代表调用成员函数 get() 和 set()
    asc[0] = 2
    println(asc[0])

    // NOTE: 无装箱开销的专门的类来表示原生类型数组: IntArray 等等。和 Array 无继承关系
}

fun testString() {
    // NOTE: 字符串
    val string = "abc"
    // 通过索引访问字符
    println(string[0])
    // 迭代字符
    for (c in string) {
        println(c)
    }

    // 原生字符串 使用三个引号（"""）分界符括起来，内部没有转义并且可以包含换行和任何其他字符:
    val text = """
        for (c in "foo")
        print(c)
    """
    println(text)

    // NOTE: 字符串模板，以美元符（$）开头，由一个简单的名字构成，或者用花括号扩起来的任意表达式:
    val i = 10
    val s = "i = $i"
    println(s)
    // 表达式
    val str = "length of s = ${s.length}"
    println(str)
}