package _mapText

/**
 * parse chunk of string, into list of parseable string
 */
interface StringParser{
    fun parse(parserString:String):List<String>
    fun joinString(list:List<String>):String
}

class StringParserImpl:StringParser{
    override fun parse(parserString: String): List<String> {
        return parserString.split("\n").filter { it.isNotBlank() }
    }

    override fun joinString(list: List<String>): String {
       return list.joinToString("\n")
    }
}