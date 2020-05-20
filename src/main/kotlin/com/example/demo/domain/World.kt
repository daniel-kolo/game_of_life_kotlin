package com.example.demo.domain

class World(val size_x: Int = 30, val size_y: Int = 30) {
    lateinit var cells: Array<Array<Cell>>

    fun setup(){
        //println("World setting up")
        cells = Array(size_x) {
            Array(size_y) {
                Cell(false,0,0, this)
            }
        }

        for(i in 0..size_x-1){
            for(j in 0..size_y-1){
                cells[i][j] = Cell(false,i,j, this)
            }
        }
    }

    fun isCellAlive(x: Int, y: Int) : Boolean{
        //println("Checking if $x $y is alive: $alive ")
        if (x >= 0 && y >= 0 && x < size_x-1 && y < size_y-1) {
            return cells[x][y].isAlive
        }
        else{
            return false
        }
    }

    fun step(){
        var newCells = Array(size_x) {
            Array(size_y) {
                Cell(false,0,0, this)
            }
        }

        for(i in 0..size_x-1){
            for(j in 0..size_y-1){
                val isAlive = cells[i][j].returnAliveAfterStep()
                //"New cell $i $j will be $isAlive"
                newCells[i][j] = Cell(this.isCellAlive(i,j),i,j, this)
            }
        }
        cells = newCells
    }

    fun setCell(x: Int, y: Int, isAlive: Boolean){
        cells[x][y].isAlive = isAlive
    }

    fun printState(){
        for (i in 0..cells.size-1){
            for (j in 0..cells.size-1){
                if (cells[i][j].isAlive){
                    print("1 ")
                }
                else {
                    print("0 ")
                }
            }
            println("")
        }
        println("--------------------------------------")
    }
}
