# Using Static Members

## Static Members

- Static Members are shared class-wide
- Not associate with individual instance
- Declared using the `static` keyword
- Available using the class name

**Static Fields**: a value not associated with a specific instance, all instances access the same value.

```java
public class Flight {
    private int passengers, int seats = 150;
    private static int allPassengers; // not accessible outside the class
    public void add1Passenger() {
        if(passengers < seats) {
            passengers +=1;
            allPassengers +=1;
        }
    }
    // ...
}
```

**Static Methods**: perform action not tied to a specific instance, it has access to static members only.

Flight.java

```java
public class Flight {
    private int passengers, int seats = 150;
    private static int allPassengers; // not accessible outside the class
    public static int getAllPassengers() {
        return allPassengers;
    }
    public static void resetAllPassengers() {
        allPassengers = 0;
    }
    // ...
}
```

Main.java

```java
Flight.resetAllPassengers();

Flight laxToSlc = new Flight(); // create a new instance of Flight
laxToSlc.add1Passenger(); // passengers = 1 and allPassengers = 1
laxToSlc.add1Passenger(); // passengers = 2 allPassengers = 2

Flight dfwToNyc = new Flight(); // create a new instance of Flight
dfwToNyc.add1Passenger(); // passengers = 1  and allPassengers = 3

System.out.println(laxToSlc.getPassengers()); // 2
System.out.println(dfwToNyc.getPassengers()); // 1
System.out.println(Flight.getAllPassengers()); // 3
```

## Static import statement

## Static initialization blocks
