package _mapText

import org.junit.Assert.*
import org.junit.Test

internal class StringParserImplTest{
    @Test
    fun should_parse_three() {
        val stringValue = "1\n2\n3"
        val parser = StringParserImpl()
        val result = parser.parse(stringValue)
        assertEquals(3, result.size)
    }

    @Test
    fun should_parse_two(){
        val stringValue = "1\n2\n"
        val parser = StringParserImpl()
        val result = parser.parse(stringValue)
        assertEquals(2, result.size)
    }
}