package com.hotbody.kotlindemo

// NOTE: 如果出现名字冲突，可以使用 as 关键字在本地重命名冲突项来消歧义：
import com.hotbody.kotlindemo.pack1.Model
import com.hotbody.kotlindemo.pack2.Model as Pack2Model

/**
 * Created: chiemy
 * Date: 17/6/9
 * Description:Kotlin 包
 */

fun test() {
    val model1 = Model()
    val model2 = Pack2Model()
}