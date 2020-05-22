package com.example.demo.domain

class World(val size_x: Int = 30, val size_y: Int = 30) {
    lateinit var cells: Array<Array<Boolean>>

    fun setup(){
        cells = Array(size_x) {
            Array(size_y) {
                false
            }
        }
    }

    fun getValidNeighboursCount(x: Int, y: Int): Int{
        var neighbours = 0
        for (i in x-1..x+1){
            for (j in y-1..y+1){
                if(!((i == x && j == y) || i<0 || i>=size_x || j<0 || j>=size_y)){
                    val size = cells.size
                    if (cells[i][j]){
                        neighbours+=1
                    }
                }
            }
        }
        return neighbours
    }

    fun step(){
        var newCells = Array(size_x) {
            Array(size_y) {
               false
            }
        }

        for(i in 0..size_x-1){
            for(j in 0..size_y-1){
                // Any dead cell with three live neighbours becomes a live cell.
                if (!cells[i][j]){
                    if (getValidNeighboursCount(i,j) == 3){
                        newCells[i][j] = true
                    }
                }

                // Any live cell with two or three live neighbours survives.
                //All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                if (cells[i][j]){
                    if  ((getValidNeighboursCount(i,j) == 2 || getValidNeighboursCount(i,j) == 3)){
                        newCells[i][j] = true
                    }
                }
            }
        }
        cells = newCells
    }

    fun setCell(x: Int, y: Int, isAlive: Boolean){
        cells[x][y] = isAlive
    }
}
