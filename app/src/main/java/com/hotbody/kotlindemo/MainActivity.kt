package com.hotbody.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById(R.id.text) as TextView
        textView.text = "kotlin"

        // delegateTest()
        // basicTest()
        // testIf()
        // testWhen()
        testReturnsAndJumps()
    }

}
