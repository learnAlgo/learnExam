package graph

import org.junit.Assert.*
import org.junit.Test

class GraphBuilderImplTest{
    @Test
    fun should_build_graph(){
        val builder = GraphBuilderImpl()
        val graph = builder.build(listOf("A1","B2","C2"), listOf("A->B", "B->C", "A->C"))
        assertEquals(3, graph.getvertexMap().keys.size)
        val listAdject = graph.getvertexMap()["A"]?.listNeightbours
        assertEquals(2, listAdject?.size?:0)

        val vertex = graph.getvertexMap()["A"]
        assertEquals(1, vertex?.weight)
        assertEquals("A", vertex?.name)
    }
}