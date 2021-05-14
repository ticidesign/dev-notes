# Methods

## Passing objects as parameters

- Passed 'by reference': a parameter receive a copy of the reference
- Changes to the reference is only visible within the method, not visible outside it.

Flight.java

```java
public class Flight {
    private int flightNumber;
    public Flight(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    // other members elided
}
```

Main.java

```java
Flight val1 = new Flight(10);
Flight val2 = new Flight(20);
// it doesn't change the reference of val1 and val2
swapFlight(val1 , val2); //val1: 10, val2: 20

static void swapFlight(Flight i, Flight j) {
    Flight k = i;
    i = j;
    j = k;
}
```

## Effect of changes to objects parameters

- Changes to members is visible within and outside the method.

Main.java

```java
Flight val1 = new Flight(10);
Flight val2 = new Flight(20);
// it doesn't change the reference  but it DOES change the members of val1 and val2
swapFlight(val1 , val2); //val1: 20, val2: 10

static void swapFlight(Flight i, Flight j) {
    int k = i.getFlightNumber();
    i.setFlightNumber(j.getFlightNumber);
    j.setFlightNumber(k);
}
```

## Methods overloading

- Allow to have multiple versions of a method or constructor within a class
- Each constructor and method must have a unique signature:
  - Number of parameters
  - Type of each parameter
  - Method name

```java
class Passenger {
    //overloading contrusctors with unique signature:
    Passanger() {...}
    Passanger(int freeBags) {...}
    Passanger(double perBagFee) {...}
    Passanger(int freeBags, int checkedBags) {...}

    // other members elided
}
```

Another example

Flight.java

```java


```

## Overload method resolution

## Variable number of parameters
