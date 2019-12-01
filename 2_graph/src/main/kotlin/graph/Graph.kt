package graph

import java.util.*

interface Graph{
    fun findMaximum(start:String):Pair<Set<String>, Int>
    fun getvertexMap():Map<String, Vertex>
}

interface GraphBuilder{
    fun build(listVertex:List<String>, listEdges:List<String>):Graph
}

class GraphBuilderImpl:GraphBuilder{
    override fun build(listVertex: List<String>, listEdges: List<String>): Graph {
       var mapVertex = listVertex.map{
           val weight = it.substring(1).toInt()
           val name = it.substring(0,1)
           Vertex(name, weight, emptySet())
       }.map { it.name to it }.toMap()

        listEdges.forEach {
            val split = it.split("->")
            val vertex = mapVertex[split[0]]!!
            val nextVertex = split[1]
            val edgeList = vertex.listNeightbours + nextVertex
            val newVertex = vertex.copy(listNeightbours = edgeList)
            val newMap = mapVertex.toMutableMap().apply { put(newVertex.name, newVertex) }
            mapVertex = newMap

        }
        return SomeGraphImpl(mapVertex)
    }
}


class SomeGraphImpl(val mapVertex:Map<String,Vertex>):Graph{
    override fun findMaximum(start:String): Pair<Set<String>, Int> {
        val stack = Stack<String>()
        val visited = mutableMapOf<String, Boolean>()
        val parent = mutableMapOf<String,String>()
        val possibleOutCome = mutableListOf<Set<String>>()
        mapVertex.keys.forEach {
            visited.put(it, false)
            parent.put(it,"")
        }

        stack.push(start)

        while(stack.empty().not()){
            val entryKey = stack.pop()
            visited[entryKey] = true
            val vertex = mapVertex[entryKey]!!

            visit(vertex, parent, possibleOutCome)

            if(vertex.listNeightbours.size > 0){
                vertex.listNeightbours.forEach {
                        stack.push(it)
                        parent[it] = entryKey
                }
            }
        }

        return possibleOutCome.map{
            val totalWeight = it.fold(0){cummulative, key ->
                mapVertex[key]!!.weight+ cummulative
            }
            Pair(it, totalWeight)
        }.sortedByDescending { it.second }.first()
    }

    private fun visit(vertex:Vertex,parent:Map<String,String>, possibleOutcome:MutableList<Set<String>>){
        if( vertex.listNeightbours.size == 0){
            val set = mutableSetOf<String>()
            var current = vertex.name
            set.add(current)

            while(current !=""){
                current = parent[current]?:""

                if(current !="")
                    set.add(current)

            }

            possibleOutcome.add(set)

        }
    }

    private fun printParent(mapParent:Map<String, String>, start:String, end:String){
        var current = end
        while(current != start){
            current = mapParent[current]!!
        }
        println()
    }


    override fun getvertexMap(): Map<String, Vertex> {
        return mapVertex
    }

}