package _mapText

interface ArrayMapFactory{
    fun generateFromString(keyValueString:String):Array<ArrayMap>
    fun create(size:Int):Array<ArrayMap>
    fun save(array:Array<ArrayMap>):String
}

/**
 * factory to create and save arrayMap
 */
class ArrayMapFactoryImpl(private val stringParser:StringParser,
                          private val representationParser: RepresentationParser):ArrayMapFactory{
    override fun generateFromString(keyValueString: String): Array<ArrayMap> {
        return stringParser.parse(keyValueString)
                .map { representationParser.load(it) }
                .map{map->  val arrayMap:ArrayMap = ArrayMapImpl(representationParser)
                    map.entries.fold(arrayMap){arrayMap_internal, entries ->
                        arrayMap_internal.put(entries.key, entries.value)
                        arrayMap_internal
                    }
                }.toTypedArray()
    }

    override fun create(size: Int): Array<ArrayMap> {
        return (0..size-1).map { ArrayMapImpl(representationParser) }.toTypedArray()
    }

    override fun save(array: Array<ArrayMap>): String {
       return stringParser.joinString(array.map { it.getRepresentation() })
    }
}