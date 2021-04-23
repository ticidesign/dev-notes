# Implementing Class Constructors and Initializers

## Default initial state of fields

When an object is create, it is expected to be in a useful state. Java provides default fields values.

Three ways to establish initial state:

- Field initializers
- Constructors
- Initialization blocks

## Field initializers

Specfiy fields's initial value as part of the field's declaration (can be an equation, can include other fileds, can include methods calls)

```java
public class Earth {
    long circumferenceInMiles = 24901;
    long circumferenceInKms = Math.round(circumferenceInMiles * 1.6d);
}
```

## Constructors

Code that runs during object creation

- accept zero or more parameters
- can have multiple constructors
- named same as the class
- no return type

Flight.java

```java
class Flight {
    int passengers;
    int seats = 150;

    // /No need for the constructor here!!!!
    // Flight() {
    //     seats = 150;
    //     passengers = 0;
    // }
}
```

## [Chaining constructors](https://www.geeksforgeeks.org/constructor-chaining-java-examples/)

One constructor can call another

- call must be first line of the constructor
- can pass parameters
- use the `this` keyword followed by parameter list

Passenger.java

```java
public Passanger()

public Passanger(int freeBags) {
    this(freeBags > 1 ? 25.0d : 50:0d);
    this.freeBags = freeBags;
}

public Passanger(int freeBags, int checkedBags) {
    this(freeBags)
    this.checkedBags = checkedBags;
}

public Passanger(double perBagFee) {
    this.perBagFee = perBagFee;
}
```

## Constructors visibility

Constructors can be non-public

- limits which code can perform specifc types of instance creation

Passenger.java

```java
public Passanger()
public Passanger(int freeBags)
public Passanger(int freeBags, int checkedBags)
private Passanger(double perBagFee) // private constructor just accessible from other constructors

```

Main.java

```java
Passenger cheapJoe = new Passenger(0.01d); // ERROR: it doesn't access the private contructor
Passenger geetha = new Passenger(2);
Passenger santiago = new Passenger(2, 3);
```

## Initialization blocks

Allow to have code that is shared across all constructors

- code that runs during object creation
- not tied to any specific constructor
- cannot receive parameter
- place code within brackets outside of any method or constructor

A class can have muliple initialization blocks if you provide more they will all always execute in order starting at the top of the source file.

Flight.java

```java
class Flight {
    private int passengers, int seats = 150;
    private int flightNumber;
    private char flightClass;
    private boolean[] isSeatAvailable = new boolean[seats];

    // Initializing with Constructors
    // public Flight() {
    //     for(int i = 0; i < seats; i++) {
    //         isSeatAvailable[i] = true;
    //     }
    // }
    // public Flight(int flightNumber) {
    //     this();
    //     this.flightNumber = flightNumber;
    // }
    // public Flight(int flightClass) {
    //     this();
    //     this.flightClass = flightClass;
    // }

    // Using Initialization block instead
    {
        for(int i = 0; i < seats; i++) {
            isSeatAvailable[i] = true;
        }
    }
    public Flight(int flightNumber) {
        this.flightNumber = flightNumber;
    }
    public Flight(int flightClass) {
        this.flightClass = flightClass;
    }
    public Flight(){}
    // other members elided
}
```

## Initialization and Constructors order

1. Field initializers
2. Initialization blocks
3. Constructors
