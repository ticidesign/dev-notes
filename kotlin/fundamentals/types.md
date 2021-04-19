# Types in kotlin

- Basic definition
- Default Methods
- Proprerties
- Classes
- Inheritance
- Construction
- Data Classes

## Interfaces

- [Interfaces](https://kotlinlang.org/docs/interfaces.html) are public by default

```kotlin
interface A { fun doSomething() = {} }
interface B { fun doSomething() = {} }

class Foo: A, B {// extends A and B
    override fun doSomething() = {
        super<A>.doSomething()
    }
}
```

Example

```kotlin
interface Time {
    fun setTime(hours: Int, mins: Int = 0, secs: Int = 0)
    fun setTime(time: KevinTime) = setTime(time.hours)
}

interface EndOfTheWorld {
    fun setTime(time: KevinTime) {}
}

class KevinTime {
    var hours: Int = 0
    var minutes: Int = 0
    var seconds: Int = 0
}
class YetiTime : Time, EndOfTheWorld {
    override fun setTime(time: KevinTime) {
        super<Time>.setTime(time)
    }
   override fun setTime(hours: Int, mins: Int, secs: Int) {}
}
```

## Classes

- Everuthing is public by default
- No 'package-private'
- But does have 'intenal'

_'final' by default_

```kotlin
class Student : Person {    // Class is final by default
    fun getClasses() {     // Methods are final by default
    }
}

open class Student : Person {    // Use open to show class can be derived from
    open fun getClasses() {     // Use open to show functions can be overridden
    }
}
```

_'abstract' classes_

```kotlin
abstract class Person {         // Class is abstract
    abstract fun getName() {    // getName must be implemented
    }
    open fun workHard() {       // workHard may be overridden
    }
    fun goOnHoliday() {         // goOnHolidyas cannot be overridden
    }
}

open class Student : Person {    // Use open to show class can be derived from
    open fun get Classes() {     // Use open to show functions can be overridden
    }
}
```

```kotlin
abstract class Person2 {
    var firstName: String = " "
    var lastName: String = " "

    open fun getName() : String = "$firstName $lastName"
    abstract fun getAddress(): String
}

class Student :  Person2() {
    override fun getAddress(): String {
        return ""
    }
    override fun getName() : String{return " "}
}
```

_Seladed Classes_

- Used to restrict cass Hierarchies
- Enums on steroides

```kotlin
sealed class Events {
    class SendEvent(id: int, to: String) : Event()
    class ReceiveEvent(id: Int, from: String) : Event()
}

// Using Sealed Classes
fun handleEvent(e:Event) =
    when(e){
        is SendEvent -> print(e.to)
        is ReceiveEvent -> print(e.from)
    }
```

LightBulb.kt

```kotlin
package com.rsk

sealed class PersonEvent{
    class Awake : PersonEvent()
    class Asleep : PersonEvent()
    class Eating(val food: String) : PersonEvent()
}

fun handlePersonEvent(event: PersonEvent) {
    when(event) {
        is PersonEvent.Awake -> println("Awake")
        is PersonEvent.Asleep -> println("Asleep")
        is PersonEvent.Eating -> println(event.food)
    }
}
```

_Constructors_

- Private constructors are supported
- Usually used to inhibit construction
  - eg to create a singleton
- in Kotlin there is a better way

Primary constructor usage

```kotlin
// example 1 (*Preferable usage)
open class Person(val name: String) {}

// example 2
open class Person(name: String) {
    val name: String
    init{
        this.name = name
    }
}

// example 3
open class Person (_name: String) {
    val name = _name
}
```

Secondary constructor usage

```kotlin
open class Person(val name: String) {
    constructor(name: String, age: Int) : this(name)
}
//*Preferable usage as a primary construction
open class Person(name: String, age = 0) {}
```

Calling Superclass Constructors

```kotlin
class Student(name: String) : Person(name)

class Student: Person {
    constructor(name: String) : super(name)
}
```

_Data classes_

- Provide a convenient way to override equal, hashCode and toString
- Typically immutable classes
- Kotlin also generates 'copy' method

Using data classes

```kotlin
data class Meeting(val name:String, val location: String)
val aMeeting = Meeting("A meeting", "London")
val anotherMeeting = aMeeting.copy(location = "New York")
```

_Summary_

- Interface can have default methods
- Classes are similar in many ways to Java
- Constructors are part of class definition
- Constructors can take default parameters
- 'final' by default
- 'sealed' restricts type hierarchy
