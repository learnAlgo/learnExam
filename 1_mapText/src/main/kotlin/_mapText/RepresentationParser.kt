package _mapText

/**
 * class to convert from map to string and vice versa
 */
interface RepresentationParser{
    fun parse(map:Map<String, String>):String
    fun load(keyValue:String):Map<String,String>
}

class RepresentationParserImpl:RepresentationParser{
    override fun parse(map: Map<String, String>): String {
        return map.map{ mapKey -> "${mapKey.key}=${mapKey.value}"}.joinToString(";")
    }

    override fun load(keyValue: String): Map<String, String> {
        return keyValue.split(";").map{ mapValue ->
            val resultSplit = mapValue.split("=")

            if(resultSplit.size != 2)
                throw Exception("parsing error")

            resultSplit[0] to resultSplit[1]
        }.toMap()
    }

}