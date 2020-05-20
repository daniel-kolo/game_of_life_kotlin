package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.test.Person
import com.sun.org.apache.xpath.internal.operations.Bool
import javafx.collections.FXCollections
import javafx.scene.layout.GridPane
import tornadofx.*
import com.example.demo.domain.Cell
import com.example.demo.domain.World
import javafx.beans.property.ReadOnlyBooleanProperty
import javafx.scene.control.Button
import javafx.scene.control.ToggleButton
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.column
import java.util.*
import java.util.logging.Handler
import kotlin.system.exitProcess


class MainView : View("Hello TornadoFX") {
    val x: IntArray = intArrayOf(1, 2, 3)
    override val root = hbox {
        label(title) {
            addClass(Styles.heading)
        }

    }
}

class DemoTableView : View() {
    override val root = GridPane()

    val mapTableContent = mapOf(Pair("", ""), Pair("", ""), Pair("", ""))


    init {
        with (root) {
            row {
                vbox {
                    /*tableview(FXCollections.observableArrayList<Map.Entry<String, Int>>(mapTableContent.entries)) {
                        readonlyColumn("asd", )
                    }*/

                    tableview(FXCollections.observableArrayList<Map.Entry<String, String>>(mapTableContent.entries)) {
                        readonlyColumn("", Map.Entry<String, String>::key)
                        readonlyColumn("", Map.Entry<String, String>::value)
                        readonlyColumn("", Map.Entry<String, String>::value)
                        readonlyColumn("", Map.Entry<String, String>::value)
                        readonlyColumn("", Map.Entry<String, String>::value)
                        readonlyColumn("", Map.Entry<String, String>::value)
                        readonlyColumn("", Map.Entry<String, String>::value)
                        resizeColumnsToFitContent()
                    }
                }
            }
        }
    }

}



class GuideView : View() {

    override val root = VBox()
    val WORLD_SIZE = 5
    val world = World(WORLD_SIZE, WORLD_SIZE)

    init {
        world.setup()
        with(root) {
            for(i in 0..WORLD_SIZE-1){
                hbox {
                    for(j in 0..WORLD_SIZE-1) {
                        togglebutton("") {
                            val x = j
                            val y = i
                            this.isSelected = false
                            action {
                                world.setCell(x,y,this.isSelected)
                            }
                            setPrefSize(30.0, 30.0)
                        }
                    }
                }
            }
            togglebutton("STEP") {
                action{
                    step()
                }
            }
        }
    }

    fun step(){
        //println("UI stepping")
        //println("Stepping")
        //world.printState()
        world.step()

        var boxes = root.getChildList()
        boxes?.forEach {
            val outerSize = boxes.size
            for (i in 0..outerSize-2){
                val childList = boxes.get(i).getChildList()
                val innerSize = childList?.size
                for (j in 0..innerSize!!-1){
                    val toggleButton = childList?.get(j) as? ToggleButton ?: return
                    //print(world.isCellAlive(i,j))
                    toggleButton.isSelected = world.isCellAlive(i,j)

                    //println(toggleButton.getChildList()?.get(0)?.toString())
                    //println(world.isCellAlive(i,j))
                    //println("$i $j")
                    //exitProcess(1)
                    //print(toggleButton.text)
                    //toggleButton.isSelected = false
                }
            }

        }
        world.printState()
        //val toggleButton = root.getChildList()?.get(0)?.getChildList()?.get(0) as? ToggleButton ?: return
        //toggleButton.isSelected = false
    }
}