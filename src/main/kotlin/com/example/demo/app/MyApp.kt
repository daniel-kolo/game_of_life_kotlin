package com.example.demo.app

import com.example.demo.test.PersonEditor
import com.example.demo.view.DemoTableView
import com.example.demo.view.GuideView
import com.example.demo.view.MainView
import tornadofx.App

class MyApp: App(GuideView::class, Styles::class)

/*
class TableViewApp : App() {
    override val primaryView = DemoTableView::class
}

fun main(args: Array<String>) {
    Application.launch(TableViewApp::class.java, *args)
}*/