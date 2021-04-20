# Infinite Collections

Functions, such as filter and map, create lists it's fine ig lists are small but it's not good if lists are mavise
Insteade use [Sequences](https://kotlinlang.org/docs/sequences.html)

## Sequences

Squences have terminal and non-terminal operations on them, they are lazily evaluated and the evaluation starts when using the terminal operation

```kotlin
package rsk

fun main(args: Array<String>) : Unit {

    val meetings = listOf(Meeting(1, "Board Meeting"), Meeting(2, "Committee Meeting"))

    val titles: Sequence<String> = meetings
            .asSequence()
            .filter {println("filter($it)"); it.title.endsWith("g")}
            .map { println("map($it)"); it.title}


   for (t in titles) println(t) // terminal operation

   val title = meetings
           .asSequence()
           .map{ println("map($it)"); it.title.toUpperCase()}
           .find { it.startsWith("BOARD")}

   println(title)
}

class Meeting(val id: Int, val title: String) {
    val people = listOf(Person("Sam"), Person("Alex"))
}

data class Person(val name: String) {}

```

_Summary_

- Similar to lists
- Lazily evaluated
- Prefer squences to lists
- Like Java 8 Streams (because streams are not available in Android)
