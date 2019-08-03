package com.zebra.isv.googlebookskotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        try {
//            this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {
//        }

        setContentView(R.layout.activity_main)    }
}
