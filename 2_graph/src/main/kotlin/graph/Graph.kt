package graph

import java.util.*

interface Graph {
    fun findMaximum(start: String): Int
    fun getvertexMap(): Map<String, Vertex>
}

interface GraphBuilder {
    fun build(listVertex: List<String>, listEdges: List<String>): Graph
}

class GraphBuilderImpl : GraphBuilder {
    override fun build(listVertex: List<String>, listEdges: List<String>): Graph {
        var mapVertex = listVertex.map {
            val weight = it.substring(1).toInt()
            val name = it.substring(0, 1)
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


class SomeGraphImpl(val mapVertex: Map<String, Vertex>) : Graph {
    override fun findMaximum(start: String): Int {
        val finish = mutableSetOf<String>()
        val explored = mutableSetOf<String>()
        val distance:MutableMap<String, Pair<Set<String>,Int>> = mutableMapOf()

        mapVertex.keys.forEach {
            distance.put(it, Pair(emptySet(),0))
        }

        val startVertex = mapVertex[start]!!
        explored.add(startVertex.name)
        distance.put(startVertex.name, Pair(setOf(startVertex.name), startVertex.weight) )

        while(explored.isNotEmpty()){
            val currentVertex = explored.first().let{mapVertex[it]!!}
            val (setCurrent,distanceCurrent) = distance[currentVertex.name]!!

            //update table
            currentVertex.listNeightbours.forEach {
                val nextVertex = mapVertex[it]!!
                val (set,value) = distance[nextVertex.name]?:Pair(emptySet(),0)
                val isCombineBigger =  distanceCurrent + nextVertex.weight > value
                val isNotPartOfSolution = set.contains(nextVertex.name).not()
                if(isCombineBigger && isNotPartOfSolution){
                    val newSet = set + currentVertex.name
                    val newDistance = distanceCurrent + nextVertex.weight
                    distance[nextVertex.name] = Pair(newSet, newDistance)
                }
                if(finish.contains(nextVertex.name).not()) {
                    explored.add(nextVertex.name)
                }
            }

            explored.remove(currentVertex.name)
            finish.add(currentVertex.name)
        }
        return distance.maxBy { it.value.second }?.value?.second?:0
    }

    override fun getvertexMap(): Map<String, Vertex> {
        return mapVertex
    }
}