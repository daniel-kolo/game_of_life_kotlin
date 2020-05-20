package com.example.demo.domain


class Cell(var isAlive: Boolean = false, val x: Int, val y: Int, val world : World) {

    var neighbours = ArrayList<Pair<Int,Int>>(8)

    init {
        var neighbours = arrayListOf(Pair(0,0),
                Pair(0,0),
                Pair(0,0),
                Pair(0,0),
                Pair(0,0),
                Pair(0,0),
                Pair(0,0),
                Pair(0,0))
        var index = 0
        for (i in x-1..x+1){
            for (j in y-1..y+1){
                if (i == x && j == y ){
                    continue
                }
                neighbours[index] = Pair(i,j)
                index +=1
            }
        }
        this.neighbours = neighbours
    }

    fun getAliveNeighbours(): Int{
        val x = this.x
        val y = this.y
        var aliveCounter = 0
        for (i in 0..neighbours.size-1){
            if (neighbours.size==0) {
                return 0
            }

            if (x ==1 && y == 1){
                val isNeighbourAlive = world.isCellAlive(neighbours.get(i).first,neighbours.get(i).second)
                val neighbour = neighbours.get(i)
                println("$neighbour is $isNeighbourAlive")
            }

            if (world.isCellAlive(neighbours.get(i).first,neighbours.get(i).second)){
                aliveCounter += 1
            }
        }

        if (x ==1 && y == 1){
            println(neighbours)
            println(aliveCounter)
        }
        //println("Checking neighbours of $x $y : $aliveCounter")
        return aliveCounter
    }

    fun returnAliveAfterStep(){
        // Any dead cell with three live neighbours becomes a live cell.
        if (!this.isAlive){
            if (this.getAliveNeighbours() == 3){
                this.isAlive = true
            }
        }

        // Any live cell with two or three live neighbours survives.
        //All other live cells die in the next generation. Similarly, all other dead cells stay dead.
        if (this.isAlive){
            if  (!(this.getAliveNeighbours() == 2 || this.getAliveNeighbours() == 3)){
                this.isAlive = false
            }
        }
    }
}