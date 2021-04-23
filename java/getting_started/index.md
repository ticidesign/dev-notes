# Getting Started

In Java, every application begins with a class name, and that class must match the filename.

Let's create our first Java file, called Main.java, which can be done in any text editor (like Notepad).

The file should contain a "Hello World" message, which is written with the following code:

```java
public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World");
  }
}
```

## Types

- Primitive data types - includes `byte`, `short`, `int`, `long`, `float`, `double`, `boolean` and `char`
- Non-primitive data types - such as `String`, `Arrays` and `Classes`

## Comments

```java
// This is an inline comment
System.out.println("Hello World");
System.out.println("Hello World"); // This is an inline comment

/* This is a multiple line comment and the code below will print the words Hello World to the screen, and it is amazing */
System.out.println("Hello World");

/**
* This is a Javadoc comments and it could be used to generate documentation. What it does is
* provide a syntax that allows us to write documentation for our code directly in the source
* code. So there's a utility called Javadoc that can generate that documentation.
*/
System.out.println("Hello World");
```

## Variables

Declaring (Creating) Variables

Syntax: `type variable = value;`

## Data types

### Time amd Date

- Time of events: primarily insterested in sequencing and timestamp
- Local human-friendly time: date and/or time of day
- Global human-friendly time: date and time of day, understands time zones

**Instant class**

- Optimized for time-stamping events
- Works well for relative time comparisons
- Can be converted into more complex date/time types

```java
static void checkRelationship(Instant otherInstant) {
    Instant nowInstant = Instant.now()
    if(otherInstant.compareTo(nowInstant) > 0)
        System.out.println("Instant is in the future");
    else if (otherInstant.compareTo(nowInstant) < 0)
        System.out.println("Instant is in the past");
    else
        System.out.println("Instant is now");
}
```

**Local human-friendly time**

| Class               | Description                                                            |
| ------------------- | ---------------------------------------------------------------------- |
| `LocalDate`         | Represents a date (year, month, day (yyyy-MM-dd))                      |
| `LocalTime`         | Represents a time (hour, minute, second and nanoseconds (HH-mm-ss-ns)) |
| `LocalDateTime`     | Represents both a date and a time (yyyy-MM-dd-HH-mm-ss-ns)             |
| `DateTimeFormatter` | Formatter for displaying and parsing date-time objects                 |

- Focuses on the date and/or time value (no time zone)
- Provide common operations (finding differences, increasing/decreasing values, manipulating content, convert to/from string)

**Global human-friendly time**

| Class          | Description                                                                             |
| -------------- | --------------------------------------------------------------------------------------- |
| `ZoneDateTime` | Operations similar to `LocalDateTime`, understands time zones, strong time zone support |

**Formatting Date and Time**

You can use the `DateTimeFormatter` class with the `ofPattern()` method in the same package to format or parse date-time objects.

```java
LocalDate today = LocalDate.now(); //2021-14-01
DateTimeFormatter.ofPattern("MM-dd-yyyy"); //(01-14-2021)

```

Example

```java
package com.pluralsight.slidedatetimeexample;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {

    public static void main(String[] args) {
        instantExample();
        dateFormatterExample();
    }

    static void instantExample() {
        String futureInstantString = "2050-01-01T12:00:00.00Z";
        String pastInstantString = "2000-01-01T12:00:00.00Z";

        System.out.println("**************************************");
        System.out.println("Instant comparison");
        System.out.println();
        System.out.println("Comparing: " + futureInstantString);
        Instant futureInstant = Instant.parse(futureInstantString);
        checkRelationship(futureInstant);

        System.out.println();
        System.out.println("Comparing: " + pastInstantString);
        Instant pastInstant = Instant.parse(pastInstantString);
        checkRelationship(pastInstant);
        System.out.println("**************************************");
        System.out.println();

    }

    static void checkRelationship(Instant otherInstant) {
        Instant nowInstant = Instant.now();
        if(otherInstant.compareTo(nowInstant) > 0)
            System.out.println("Instant is in the future");
        else if(otherInstant.compareTo(nowInstant) < 0)
            System.out.println("Instant is in the past");
        else
            System.out.println("Instant is now");
    }

    private static void dateFormatterExample() {
        System.out.println("**************************************");
        System.out.println("Date formatting");
        System.out.println();

        LocalDate today = LocalDate.now();
        System.out.println("Today in default format: " + today);

        DateTimeFormatter usDateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        System.out.println("Today in US format: " + today.format(usDateFormat));

        System.out.println();

        String dateStringInUSFormat = "07-04-2022";
        LocalDate dateFromUSFormat = LocalDate.parse(dateStringInUSFormat, usDateFormat);
        System.out.println("Date string value: \"" + dateStringInUSFormat + "\"");
        System.out.println("  ... parsed in US format then displayed in default format: " + dateFromUSFormat);

        System.out.println("**************************************");
        System.out.println();
    }

}
```

## Wrapper Classes

Wrapper classes provide a way to use primitive data types (`int`, `boolean`, etc..) as objects.

```java
ArrayList<int> myNumbers = new ArrayList<int>(); // ERROR: Invalid
ArrayList<Integer> myNumbers = new ArrayList<Integer>(); // Valid
```

## Classes

- Contain state
- Contain code to manipulate that state
- Allow us to create custom data types

## Interfaces

- Model data type behaviour
- Create contracts between data types
