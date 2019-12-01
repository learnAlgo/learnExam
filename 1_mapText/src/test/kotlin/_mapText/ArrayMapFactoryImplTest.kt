package _mapText

import org.junit.Assert.*
import org.junit.Test

class ArrayMapFactoryImplTest{
    @Test
    fun should_create_n_array(){
        val parser = StringParserImpl()
        val representationParser = RepresentationParserImpl()
        val factory = ArrayMapFactoryImpl(parser,representationParser)
        val result = factory.create(5)
        assertEquals(5, result.size)
    }

    @Test
    fun should_parse_and_create_some_thing(){
        val mapStringValue = "hello=hi;hi=hello\nhello=hi;hi=hello"
        val parser = StringParserImpl()
        val representationParser = RepresentationParserImpl()
        val factory = ArrayMapFactoryImpl(parser,representationParser)

        val array = factory.generateFromString(mapStringValue)
        assertEquals(2, array.size)
        assertEquals("hi", array[0].get("hello"))
        assertEquals("hello", array[0].get("hi"))
        assertEquals("hi", array[1].get("hello"))
        assertEquals("hello", array[1].get("hi"))
    }

    @Test
    fun should_convert_to_string(){
        val parser = StringParserImpl()
        val representationParser = RepresentationParserImpl()
        val factory = ArrayMapFactoryImpl(parser,representationParser)

        val mapArray = factory.create(1)
        mapArray[0].put("hello","hello")
        mapArray[0].put("1","1")
        val mapArray2 = factory.create(1)
        mapArray2[0].put("guten","tag")
        mapArray2[0].put("good","morning")

        val combine = mapArray + mapArray2
        val stringValue = factory.save(combine)
        assertEquals("hello=hello;1=1\nguten=tag;good=morning", stringValue)

        //should be generated again
        val result = factory.generateFromString(stringValue)
        assertEquals(2, result.size)
        assertEquals("morning", result[1].get("good"))
    }
}