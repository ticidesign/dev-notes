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

### Examples

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

## Annotations

Kotlin understands annotation `@Nullable`, `@NotNull` from javax.annotation, android.support.annotation, org.jetbrains.annotation.

If there are no annotations, kotlin will work with 'Platform' type and it's the developer full responsability for doing `null` checks.

Platform types are hidden but you can see then in error messages

```kotlin
val i: Int = person.name
ERROR: Type mismatch: inferred type is String! but Int was expected
```

## Overriding Java Methods

What about overriding Java Types? Can make the parameters Nullable or not.

### Examples

Meeting.java

```java
package com.rsk.java;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Meeting {
    private String title;

    public void addTitle(@NotNull String title) {
        this.title = title;
    }

    public @Nullable String meetingTitle() {
        return title;
    }

    public String titleCanBeNull() {
        return title;
    }
}
```

Address.java

```java
package com.rsk.java;

public interface Address {
    String getFirstAddress();
}
```

Organizer.kt

```kotlin
package com.rsk.kotlin

import com.rsk.java.Address
import com.rsk.java.Meeting

fun main(args: Array<String>) {
    val m = Meeting()
//    m.addTitle("Title")
//    m.addTitle(null)

//    val title: String? = m.meetingTitle()
//    println(title)
    val title: String = m.titleCanBeNull() ?: "nobody"
    println(title)
}
class HomeAddress : Address {
    override fun getFirstAddress(): String {
        return ""
    }
}

class WorkAddress : Address {
    override fun getFirstAddress(): String? {
        return ""
    }
}
```

## Summary

- Kotlin has excellent Java interoperability
- Works with annotated types
- And non-annotated (Platform) types
