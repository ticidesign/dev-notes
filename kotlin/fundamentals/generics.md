# Understand Kotlins Improved Generic Support

## How to declare generics in Kotlin

_Inferring Types_

Kotlin is able to infer the type of the variable, see there is 3 ways to declare a variable:

```kotlin
val meetings = listOf(Meeting("Board"), Meeting("Finance"))
val meetings = mutableListOf<Meeting>()
val meetings: List<Meeting> = mutableListOf()
```

We can use generics to specify a more explicit type when we set up collections for example.

_Generic Functions_

We can also in Kotlin declare generic functions. So here we have a function that's an extension of list called `itemAt`, and this `itemAt` function works on a List of type `T`. `T` in this case is called the type parameter.

```kotlin
fun <T> List<T>.itemAt(ndx: Int) : T {}
```

_Declaring Generic Classes_

```kotlin
class Node<T> (private val item:T) {
    fun value() : T {
        return item
    }
}
```

In this case, we have a Node class, Node is of type `T`, the class has a constructor parameter called `item` that's also of type `T`, and we have a method within the class called `value` that returns an `item`. The item it return is, again, of type `T`. So in all of these cases, we're using `T` as the type parameter to declare what type this class holds in this case.

Example

```kotlin
package com.rsk

fun main (args: Array<String>) {
    var names = listOf("Kevin", "Alex")

    val name = names.itemAt(1)
    println(name)

    val n:Node<Int> = Node(2)
    println(n.value())
}
fun <T> List<T>.itemAt(ndx: Int) : T {
    return this[ndx]
}

class Node<T>(private val item: T) {
    fun value():T {
        return item
    }
}
```

Type Parameters Constraits

```kotlin
class Node<T: Number>(private val item: T) {
    fun value():T {
        return item
    }
}
```

## [reifeid types](https://kotlinlang.org/docs/inline-functions.html#reifeid-type-parameters) and how they help

Reify means to make into a thing, make it real.

- Java erases all generic type information at runtime
- Kotlin can reify some of this information

Generic functions would have their types erased at at runtime, however we can mark types as `reifeid` to be able to use this information and it only works on inline functions

```kotlin
fun <T> foo(value: Any) = value is T
Error:(8, 36) Kotlin: Cannot check for instance of erased type: T
```

Marked the function as `inline` and mark the type as `reifeid` to fix the error

```kotlin
inline fun <reifeid T> foo(value: Any) = value is T
```

_Non-inline Type Parameters_
Sometimes need to make a parameter not- inline when the function is inline

```kotlin
package com.rsk

fun main(args: Array<String>) {
    val meeting = listOf(BoardMeeting(), BoardMeeting(), FinanceMeeting())
    val board = meeting.typeOf<BoardMeeting>()

    println(board.count()) //2
}
inline fun <reified T> List<*>.typeOf() : List<T> {
    val returnList = mutableListOf<T>()

    for(item in this) {
        if(item is T) {
            returnList.add(item)
        }
    }
    return returnList
}

fun <T: Meeting> buildMeeting(meetingClass: Class<T>, action: (T) -> Unit) : T {
    val meeting: T = meetingClass.newInstance()
    action(meeting)
    return meeting
}

inline fun <reified T : Meeting> buildMeeting(noinline action: (T) -> Unit): T {
    return buildMeeting(T::class.java, action)
}

open class Meeting {}
class BoardMeeting : Meeting() {}
class FinanceMeeting : Meeting() {}
```

## [Variance](https://kotlinlang.org/docs/generics.html#variance)

Types ans Sub Types

- Types have a relationship
  - e.g. Student is a sub type of Person
- Generic types have a more complex relationship
  - Is List<Student> a sub type of List<Person>?

**Variance determines whether a subtype can be used in place of a type**

```kotlin
var meetings: MutableList<FinanceMeeting> = mutableListOf() meetings.add(Meeting())
meetings.add(Meeting()) // not safe
```

- Java users 'Super' and 'Extends'
- Josh Bloch [PECS](https://stackoverflow.com/questions/2723397/what-is-pecs-producer-extends-consumer-super)
  - Producer extends and Consumer super
- Use site variance

Kotlin Variance

- In Kotlin we use 'in' and 'out' keyword
- Declaration site variance, generally more elegant solution
- Kotlin also support 'use site' variance

If types are Covariant

- If types are Covariant
  - Derived type can be used where base type is more specific
- In Kotlin mark the type parameter as 'out'

Contravariance

- If types are Contravariant
  - Base type can be used where derived type is more specific
- If Kotlin mark a parameter as 'in'

Co and Contra Variance Example

```kotlin

```

Call Site Variance

- Sometimes need more flexibility
- Mutable list is now 'projected', what it means is that on that type, we can only call methods that return data. We can't add things into that list for example.

```kotlin
fun <T> copyData(source: MutableList<out T>, destination: MutableList<T>) {
    for (item in source) {
        destination.add(item)
    }
}
```

_Summary_

Kotlin provides excellent support for generics. It provides generic functions and generic classes. Unlike Java, in certain circumstances, generics may be reified.

Kotlin also supports co and contravariance. Kotlin uses declaration site variance. And it provides variance with the `in` and `out` keywords at the site we declare the type we're trying to use.
