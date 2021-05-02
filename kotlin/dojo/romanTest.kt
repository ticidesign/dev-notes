package romannumeral

import io.kotlintest.specs.StringSpec
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe

class RomanNumeralTest : StringSpec() {

    init {       
        "I add I should equal to II" {
            hiker.addRomanNumeral("I", "I") shouldBe "II"
        }
        
        "I add II should equal to III" {
            hiker.addRomanNumeral("I", "II") shouldBe "III"
        }
        
        "I add IV should equal to V" {
            hiker.addRomanNumeral("I", "IV") shouldBe "V"
        }
        
        "IV add I should equal to V" {
            hiker.addRomanNumeral("IV", "I") shouldBe "V"
        }
        
        "IV add III should equal to VII" {
            hiker.addRomanNumeral("IV", "III") shouldBe "VII"
        }
        
        "V add V should equal to X" {
            hiker.addRomanNumeral("V", "V") shouldBe "X"
        }    
    }
}
