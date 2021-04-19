# Getting Started

## Installation

Command line: `brew install kotlin`

[IntelliJ](https://kotlinlang.org/docs/jvm-get-started.html#run-the-application)

## What is and why use Kotlin?

- JVM language
- Object oriented
- Exception Handling
- Functional
- Less ceremony than Java
- Improves on Java in many ways
- No need even for classes (Java demands class code!)

Example

main.kt

```kotlin
import com.rsk.Person
import java.io.FileReader
import java.io.IOException
import java.lang.NumberFormatException
import java.util.*

// Using Kotlin without creating a class
fun main(args: Array<String>) {
    println("hello world")

    // Support for Immutability with 'val'
    val kevin = Person("Kevin")
    println("Name is ${kevin.Name}")
    kevin.Name = "Steve"
    // String templates
    println("Name is ${kevin.Name}")
    kevin.display()
    kevin.displayWithLambda(::printName)

    val q:Question = Question()

    q.answer = "42"
    // q.question = ""
    q.display()
    println("The answer to the question ${q.question} is ${q.answer}")

    // If Else Statement
    val message: String = if(q.answer == q.correctAnswer) {
        "You are correct"
    } else {
        "Try again"
    }

    println(message)
    q.printResult()

    // Try Catch
    val number:Int? = try {
        Integer.parseInt(q.answer)

    } catch(e:NumberFormatException) {
        null
    }

    println("Number is $number")

    // Looping Constructs
    // var range = 'a'..'z'
    for (i in 1..10) {
        println(i)
    }

    var numbers = listOf(1,2,3,4,5,)

    var ages = TreeMap<String, Int>()
    ages["Kevin"] = 55
    ages["Sam"] = 24
    ages["Alex"] = 24
    ages["Harry"] = 26

    for ((name, age) in ages){
        println("$name is $age")
    }

    for((index, element) in numbers.withIndex()) {
        println("$element at $index")
    }

    // Exceptions
    var reader = FileReader("fileName")

    try {
        reader.read()
    } catch(e: IOException) {
        // TODO
    } finally {
        // TODO
    }
}

fun printName(name: String) {
    println(name)
}

class Question {
    var answer: String? = null
    var correctAnswer = "44"
    val question: String = "What is the answer to life, the universe and everything?"

    fun display() {
        println("You said $answer")
    }

    fun printResult() {
        // When Statement (good option for switch)
        when (answer) {
            correctAnswer -> {
                print("You are correct")
            }
            else -> {
                print("Try again")
            }
        }
    }
}
```

Person.kt

```kotlin
package com.rsk

class Person(var Name: String) {
    fun display() {
        println("Display: $Name")
    }

    fun displayWithLambda(func: (s: String) -> Unit) {
        func(Name)
    }
}
```
