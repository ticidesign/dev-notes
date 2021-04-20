# Filtering and Sorting Data

Functional Style and Collections

- many benefits of using functional style with collections
- can use lubrary functions
- simplify code
- may look odd if you are not used to it

## Essential Functions

**filter**

- transform collections
- filter out unwanted items
- similar to 'where' in SQL

**map**

- transform a single item in a collection
- similar to a 'select' in SQL

Example:

```kotlin
package rsk

fun main(args: Array<String>) : Unit {
val ints = listOf(1,2,3,4,5)

    val smallInts = ints.filter{ it < 4 }

// for(i: Int in smallInts) println(i)

    val squaredInts = ints.map { it*it }

// for (i: Int in squaredInts) println(i)

    val smallSquaredInts = ints
            .filter{ it < 5}
            .map{ it * it }

// for (i:Int in smallSquaredInts) println(i)

    val meetings = listOf(Meeting(1, "Board Meeting"), Meeting(2, "Committee Meeting"))

    val titles: List<String> = meetings
            .filter {it -> it.title.startsWith("C")}
            .map { m-> m.title}
    for (t in titles) println(t)

}

```

## Predicate

- Lambdas that return a boolean
  - all
  - any
  - count
  - find

Example

```kotlin
package rsk

fun main(args: Array<String>) : Unit {
    val ints = listOf(1, 2, 3, 4, 5)
    val greaterThanThree = { v:Int -> v > 3}

    var largeInts = ints.all(greaterThanThree)

    println(largeInts)

    largeInts = ints.any (greaterThanThree)

    println(largeInts)

    var numberOfLargeInts = ints.count(greaterThanThree)

    println(numberOfLargeInts)

    var found: Int? = ints.find(greaterThanThree)

    println(found)
    found= ints.find{ it > 6}

    println(found)
}

```

**flatmap**

```kotlin
package rsk

fun main(args: Array<String>) : Unit {

    val meetings = listOf(Meeting(1, "Board Meeting"), Meeting(2, "Committee Meeting"))

    val people: List<Person> = meetings
            .flatMap(Meeting::people)
            .distinct() // remove duplicates

    for (p in people) println(p.name)
}
```

_Summary_

- Kotlin collections use lambdas
- many helpful methods
- easier to read code
- one problem though
  - list could be very large
