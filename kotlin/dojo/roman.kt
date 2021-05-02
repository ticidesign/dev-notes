package romannumeral

class RomanNumeral(
  val value: String
) {
    val substitutionMap = mapOf(
        "IV" to "IIII",
        "V" to "IIIII"
    )
    
    val convertedValue = substitutionMap[value] ?: value
    
    operator fun plus(other: RomanNumeral): RomanNumeral {
        return RomanNumeral(this.convertedValue + other.convertedValue)
    }
}

// Ordering matters here since we iterate over the entries
val concatinatorMap = listOf(
    "IIIII" to "V",
    "VV" to "X"
)

fun addRomanNumeral(r1: String, r2: String): String {
    val pr1 = RomanNumeral(r1)
    val pr2 = RomanNumeral(r2)
    
    return reverseSubstituteNumeral((pr1 + pr2).value)
}

fun reverseSubstituteNumeral(r: String): String {
    var result = r

    concatinatorMap.forEach{ (key,value) -> 
                            result = result.replace(key, value)}    

    return result
}
