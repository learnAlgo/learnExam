package graph

import org.junit.Assert.*
import org.junit.Test

class VertexTest{
    @Test
    fun should_have_weight(){
        val vertex = Vertex("A", 1,setOf("B","C","D"))
        assertEquals(3, vertex.listNeightbours.size)
    }
}