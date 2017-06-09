package com.hotbody.kotlindemo

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created: chiemy
 * Date: 17/6/9
 * Description: Kotlin 委托属性
 */
fun delegateTest() {
    // 委托属性
    val e = Example()
    println(e.p)
    e.p = "New"
    // 第一次调用记录结果
    e.lazyValue
    // 后面的调用会直接返回记录的结果
    e.lazyValue
    // 线程安全的
    e.lazyValueThreadSafety

    val user = User()
    user.name = "first"
    user.name = "second"

    val student = Student(mapOf(
            "name" to "Chiemy",
            "age" to 18
    ))

    println("${student.name} is ${student.age} years old")

    var map = mutableMapOf<String, Any?>(
            "name" to "Chiemy",
            "age" to 18
    )

    val employee = MutableStudent(map)
    println("Age is ${employee.age}")
    map.put("age", 19)
    println("Age is ${employee.age}")
}

class Example {

    // NOTE: 17/6/9 自定义委托。对于可变属性（var）要提供 getValue 和 setValue方法，对于只读属性（val）提供 getValue方法
    var p: String by Delegate()

    // NOTE: 17/6/9 Lazy 延迟委托，用于只读属性
    // 函数 lazy() 接受一个 lambda 然后返回一个可以作为实现延迟属性的委托 Lazy<T> 实例来:
    // 第一次对于 get()的调用会执行（之前）传递到 lazy()的lamda表达式并记录结果,
    // 后面的 get() 调用会直接返回记录的结果。
    // 对于lazy属性的计算是线程安全的
    val lazyValueThreadSafety: String by lazy {
        "Hello"
    }
    // NOTE: 17/6/9 延迟委托 LazyThreadSafetyMode
    // LazyThreadSafetyMode.PUBLICATION 可以多线程同时访问，但只以第一个返回值作为 Lazy 实例的值
    // LazyThreadSafetyMode.NONE
    val lazyValue: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
        // 第一次会打印
        println("computed!")
        "Hello"
    }
}

class Delegate {
    operator fun getValue(example: Example, property: KProperty<*>): String {
        return "$example, thank you for delegating '${property.name}' to me!"
    }

    // 三个参数分别为：接收者，接收者属性，新的值（必须与属性同类型或是它的父类型）
    operator fun setValue(example: Example, property: KProperty<*>, s: String) {
        println("$s has been assigned to '${property.name} in $example.'")
    }

}

class User {
    // NOTE: 17/6/9 可观察属性
    // Delegates.observable() 需要两个参数：初始值和handler。
    // 这个 handler 会在每次我们给赋值的时候被调用 (在工作完成前).
    // 它有三个参数:一个被赋值的属性，旧的值和新的值：
    var name: String by Delegates.observable("<no name>") {
        prop, oldValue, newValue ->
        println("${prop.name} change from $oldValue to $newValue")
    }
}

// NOTE: 17/6/9 把属性储存在 Map 中，对于 var的变量，我们可以把只读的Map换成 MutableMap 就可以了
// 此处是只读的 Map
class Student(map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

class MutableStudent(map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int by map
}
