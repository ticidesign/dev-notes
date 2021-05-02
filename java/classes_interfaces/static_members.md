# Using Static Members

## Static Members

- Static members are shared class-wide
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

- _Import statement_ allow a type name to be used without being package-qualified
- _Static import statement_ allow method name to be use wuthout being class-qualified (used with static methods)

Main.java

```java
// import static com.packagename.pathClass.resetAllPassengers; // import with a reference to a static member
// import static com.packagename.pathClass.getAllPassengers; // import with a reference to a static member
import static com.packagename.pathClass.*; // import with a reference to all the static member in a class

resetAllPassengers(); // because of the static import we don't need to use 'Flight.resetAllPassengers();'

Flight laxToSlc = new Flight();
laxToSlc.add1Passenger();
laxToSlc.add1Passenger();

Flight dfwToNyc = new Flight();
dfwToNyc.add1Passenger();

System.out.println(getAllPassengers()); // instead of 'Flight.getAllPassengers()'
```

## Static initialization blocks

- Perform one-time type initialization, executed before type's first use and has access to static members only
- Statements are enclosed in brackets, preceded by the _static_ keyword and outside of any method or constructor

Flight.java

```java
public class Flight {
    private int passengers, int seats = 150;
    private static int allPassengers, maxPassengersPerFlight;

    // This static block will run once before the first time we use
    // the class and it will be available to all instances of our class
    static {
        AdminService admin = new AdminService();
        admin.connect();
        maxPassengersPerFlight = admin.isRestricted() ?
            admin.getMaxFlightPassengers() : Integer.MAX_VALUE;
        admin.close();
    }

    public void add1Passenger() {
        if(passengers < seats && passengers < maxPassengersPerFlight) {
            passengers +=1;
            allPassengers +=1;
        }
    }
    // other members elided
}
```
