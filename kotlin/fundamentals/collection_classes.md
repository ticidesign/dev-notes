# Collection Classes

- Collections can be Nullable
- Collections can hold null values
- Collections can be read-only or mutable
- Kotlin collections interop with Java
- Arrays in Kotlin

## Creating Collections

- listOf, setOf, mapOf
- arrayListOf, hashSetOf, hashMapOf
- mutableListOf,
  etc...

Example

```kotlin
package com.rsk

fun main(args: Array<String>) {
    // var people: List<Person?>? = null
    // people =  listOf(Person(23), Person(56))

    var people: MutableList<Person?>? = null
    people =  mutableListOf(Person(23), Person(56))

    people.add(null)

//    for (person: Person? in people) println(person?.age)
    for (person: Person in people.filterNotNull()) println(person.age)

}

class Person(val age: Int) {}
```

## Java Inter-operates with

- Kotlin collections and Java collections are the same
- **Java does not distinguish between mutable and immutable collections**
- When implement collections that contains Platform Types, you have to thing about:
  - when overriding/implementing method
  - is the collection nullable/mutable
  - are the members nullable/mutable

## Arrays

- Array is a class with type parameter
- Can create with:
  - arrayOf
  - arrayOfNulls
  - Array() function
- Primitive types: Each primitive has its own array type
  - IntArray
  - ByteArray
  - CharArray
  - etc
- Kotlin provides the same functions on arrays as on collection classes

```kotlin
package com.rsk

fun main(args: Array<String>) {
    for (i in args.indices) {
        println("$i ${args[i]}")
    }

    val items = IntArray(2)
    items[0] = 1
    items[1] = 2

    val numbers = intArrayOf(1,2,3,4,5)
    numbers.forEachIndexed { index, i -> println("$index is: $i")  }

}
```

_Summary_

- Kotlin provides an extensive set of collecdtions
- Arrays, maps, sets and lists
- Can be read-only
- Excellent interop with Java
