package _mapText

/**
 * map like data structure
 * put: put ke value
 * get: get the value
 * getRepresentation: Get the string representation
 */
interface ArrayMap{
    fun put(key:String, value:String)
    fun get(key:String):String?
    fun getRepresentation():String
}

class ArrayMapImpl(private val parser:RepresentationParser):ArrayMap{
    var internalMap = emptyMap<String,String>().toMutableMap()

    override fun put(key: String, value: String) {
        internalMap.put(key, value)
    }

    override fun get(key: String): String? {
        return internalMap.get(key)
    }

    override fun getRepresentation(): String {
        return parser.parse(internalMap.toMap())
    }

}

