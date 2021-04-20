# Using Kotlin’s Nullability Constraints to Improve Code

Kotlin provides excellent support for handling `nulls`, better and safer than Java.

- Java often throws [`NullPointerException`](https://www.geeksforgeeks.org/null-pointer-exception-in-java/)
- Need lots of defensive code
- Kotlin support 'nullable' types
- Means only explicit variables can be null

Example in Java:

```java
boolean closeMeeting(Metting meeting) {
    if (meeting.canClose) return meeting.close();
    return false;
}
```

Is this function safe? What happens if `null` is passed? Can `null` be passed?

Same example in Kotlin:

```kotlin
fun closeMeeting(m: Meeting) : Boolean {
    return if (m.canClose) m.close()
        else false
}
```

Is this function safe? Can `null` be passed? Do we need `null` checks?

With Java, if I passed `null` into the function, then I would get a `NullPointerException` within the function itself when I try to use that `null` value. However, with Kotlin, if we try and pass `null` into the function, then it's a compiler error.

So the compiler will come along and tell us that we can't actually pass `null` as a value to this function. And the error we get is `Null` cannot be a value of a non-null type Meeting. So the code doesn't actually compile. So when we declare this function, we're declaring that it takes something of type Meeting. And that declaration tells Kotlin that Meeting cannot be `null`. And because of that, when we try and pass `null`, we get the compiler check. It means that at compile time, we get `null` checks.

### What if you want to allow `null`?

A varialbe can be declared to accept `null` by adding a `?` after the type name

```kotlin
var m: Meeting? //Meeting is a nullable type
```

- Must recognize the nullability
- Cannot assign to non-null type

## Null Checking

_Safe Call (?.)_

- check for null
- calls function only if non-null reference

```kotlin
m?.method()
```

_Elvis(?:)_

- also knonw as the null-coalescing operator
- return value or null

```kotlin
newMeeting = m ?: Meeting()
```

_Safe cast (as?)_

- cast to type or returns null

```kotlin
val saveable = o as? iSavable
```

_Not-Null assertion (!!)_

- very blunt instrument
- asserts that somenbting is not `null`
- throws NPE (NullPointerException) if it is
- throws at line operator is used (not later)
- it's meant to stand out!!

```kotlin
m!!.close()
```

_'let' function_

- used to avoid explicit null chacks
- useful when passiogn Nullable values to functions expecting non-null values

```kotlin
m?.let{
    closeMeetingNonNull(m)
}
```

_Late initialized properties_

- Sometimes values cannot be initialized when declared
- Do not want to mark them as Nullable
- Use 'lateinit' instead

Example

```kotlin
package com.rsk.kotlin

fun main(args: Array<String>) {
    var m:Meeting? = null
    var newMeeting = Meeting()

    newMeeting = m ?: Meeting()

    closeMeeting(m)
    closeMeeting(newMeeting)

    m?.let{
        closeMeetingNonNull(m)
    }
}

fun closeMeetingNonNull(m: Meeting): Boolean? {
    return if (m.canClose) m.close()
    else false
}

fun closeMeeting(m: Meeting?): Boolean? {
    return if (m?.canClose == true) m?.close()
    else false
}

fun closeMeetingAssert(m: Meeting?): Boolean? {
    return if (m!!.canClose) m.close()
    else false
}
class Meeting {
    val canClose: Boolean = false
    lateinit var address: Address

    fun close(): Boolean {
        return true
    }

    fun save(o: Any) {
        val saveable = o as? ISaveable
        saveable?.save()
    }

    fun init(addr : Address) {
        address = addr
    }
}

interface ISaveable {
    fun save()
}

class Address {}
```

_Summary_
