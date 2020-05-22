package com.example.demo.view

import tornadofx.*
import com.example.demo.domain.World
import javafx.scene.control.ToggleButton
import javafx.scene.layout.VBox
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate


class MainView : View() {

    override val root = VBox()
    val WORLD_SIZE = 20
    val world = World(WORLD_SIZE, WORLD_SIZE)
    var timers = arrayOf(java.util.Timer())
    var stepDelay = 500

    fun setTimer(speed : Long){
        var timerTask = Timer("Stepping", false)
        timerTask.scheduleAtFixedRate(1, stepDelay.toLong()) {
            step()
        }
        timers[0] = timerTask
    }

    fun cancelTimer(){
        timers.get(0).cancel()
    }

    init {
        world.setup()
        with(root) {
            for(i in 0..WORLD_SIZE-1){
                hbox {
                    for(j in 0..WORLD_SIZE-1) {
                        togglebutton("") {
                            val x = i
                            val y = j
                            this.isSelected = false
                            action {
                                world.setCell(x,y,this.isSelected)
                            }
                            setPrefSize(20.0, 20.0)
                        }
                    }
                }
            }
            hbox{
                togglebutton("STEP") {
                    action{
                        step()
                        this.isSelected = false
                    }
                }
                togglebutton("START") {
                    action{
                        setTimer(stepDelay.toLong())
                        this.isSelected = false
                    }
                }
                togglebutton("STOP") {
                    action{
                        cancelTimer()
                        this.isSelected = false
                    }
                }
                togglebutton("SLOWER") {
                    action{
                        cancelTimer()
                        stepDelay += 100
                        setTimer(stepDelay.toLong())
                        this.isSelected = false
                    }
                }
                togglebutton("FASTER") {
                    action{
                        cancelTimer()
                        stepDelay -= 100
                        setTimer(stepDelay.toLong())
                        this.isSelected = false
                    }
                }
            }
        }
    }

    fun step(){
        world.step()
        var boxes = root.getChildList()
        for(i in 0..boxes!!.size-2) {
            val outerSize = boxes.size
            for (i in 0..outerSize-2){
                val childList = boxes.get(i).getChildList()
                val innerSize = childList?.size
                for (j in 0..innerSize!!-1){
                    val toggleButton = childList?.get(j) as? ToggleButton ?: return
                    toggleButton.isSelected = world.cells.get(i).get(j)
                }
            }
        }
    }
}