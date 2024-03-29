package graph

import org.junit.Assert.*
import org.junit.Test

class SomeGraphImplTest{
    @Test
    fun should_print_stack_trace(){
        val builder = GraphBuilderImpl()
        val graph = builder.build(listOf("A1","B2","C2"), listOf("A->B", "B->C", "A->C"))
        val result = graph.findMaximum("A")
        assertEquals(5, result)
    }


    @Test
    fun should_handle_cycle(){
        val builder = GraphBuilderImpl()
        val graph = builder.build(listOf("A1","B2","C2"), listOf("A->B", "B->C", "A->C","C->A"))
        val result = graph.findMaximum("A")
        assertEquals(5, result)
    }


    @Test
    fun should_handle_cycle_2(){
        val builder = GraphBuilderImpl()
        val graph = builder.build(listOf("A1","B2","C2"), listOf("A->B", "B->C", "A->C","C->A","B->A"))
        val result = graph.findMaximum("A")
        assertEquals(5, result)
    }


}