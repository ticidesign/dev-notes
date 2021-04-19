# Companion Objects

## Static Methods

- Kotlin doesn't have static methods
- However can have 'singletons'
- Use `object` keyword
- Use 'companion object' to get `statics`

The `object` keyword create a singleton (actually defines a class and creates an instance)

Such an objects can have properties, methods, initializers. But can't have a constructors

Example

```kotlin
object Meetings {
    var allMeetings = arrayListOf<Meeting>()
}
Meething.allMeetings.add(Meeething(...))
```

Courses.kt

```kotlin
package com.rsk

class Course(valid: Int, val title: String) {}
object Courses {
    var allCourses = arrayListOf<Course>()

    fun initialize() {
        allCourses.add(Course(1, "Kotlin Fundamentals"))
    }
}
```

Person.kt

```kotlin
class Student(val id: Int) : Person2() {

    fun enrolement(courseName: String) {
        val course = Courses.allCourses
                .filter{it.title == courseName}
                .firstOrNull()
    }
}
```

## Extending Objects

- Can derive from other classes/interfaces
- `object` Inheritance: Can be used where any 'intance' is used

```kotlin
object CaseInsensitiveComparator : Comparator<File> {
    override fun compare(...) : Int {}
}
```

- Nested `object`: Can be declared inside another class

```kotlin
class Person {
    object CaseInsensitiveComparator : Comparator<File> {
        override fun compare(...) : Int {}
    }
}
```

## Companion objects

Kotlin Classes do not have static members. To add that we can use 'Companion objects'
This objects are used for factory objects and static members

'static' Methods

```kotlin
class Person {
    companion object {
        fun main(args:Arrays<String>) {...}
    }
}
```

'factory' Methods

```kotlin
class Student {
    companion object {
        fun createUndergrad(name:String) : Undergraduate{...}
        fun createPostgrad(name:String) : Postgraduate{...}
    }
}
```

Using Companion Objects `@JvmStatic`

```kotlin
class Program {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

    }
}
```

_Summary_

- `object` defines a standard classes

  - can implement interfaces
  - can derive from classes

- `object` lets us create singletons
- `companion object`
  - lets us create `static` methods (annotated with the @JvmStatic annotation)
  - lets us create `factory` methods
