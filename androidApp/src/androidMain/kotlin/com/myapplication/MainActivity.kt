package com.myapplication

import MainView
import android.os.Bundle
import initKoinModule
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoinModule()
        setContent {
            MainView()
        }
    }
}
