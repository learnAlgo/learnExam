package _mapText

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class RepresentationParserImplTest{
    @Test
    fun should_parse_to_string(){
        val map = mapOf("hello" to "world", "guten" to "tag")
        val parser = RepresentationParserImpl()
        val result = parser.parse(map)
        assertEquals("hello=world;guten=tag", result)
    }

    @Test
    fun should_show_empty_string(){
        val map = emptyMap<String,String>()
        val parser = RepresentationParserImpl()
        val result = parser.parse(map)
        assertEquals("",result)
    }

    @Test
    fun should_pass_the_given_string(){
        val stringValue = "hello=world;guten=tag"
        val parser = RepresentationParserImpl()
        val result = parser.load(stringValue)
        assertEquals(2, result.keys.size)
    }

    @Test(expected = Exception::class)
    fun expecting_exception_thrown(){
        val stringValue = "hello=world;guten::tag"
        val parser = RepresentationParserImpl()
        parser.load(stringValue)
    }

}