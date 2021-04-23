# Classes and Objects

- Java is an object-oriented programming language.
- Objects encapsulate data, operations and usage semantics
- Allow storage and manupulation details to be hidden
- Separates what is to be done from how it is done

A class is a template to create objects. An object is just an instance of a class.

Classes are reference types:

- Class variables simply hold reference
- Instances created with `new` keyword.
- Multiple variables can reference the same instance.

A class is made of both state and executable code.

- **Fields**: store object state
- **Methods**: excutable code that manipulates state and performs operations
- **Constructors**: executable code used during object creation to set initial state.

Flight.java

```java
class Flight {
    // class members
    int passengers; //state field
    int seats; //state field

    //constructor
    Flight() {
        seats = 150;
        passengers = 0;
    }

    // method
    void add1Passager() {
        if(passengers < seats) {
            passengers += 1;
        }
    }
}
```

## Special References

**this**: implicit reference to current object, useful for reducing ambiguity and allows an object to pass iself as a parameter.

**null**: represents an uncreated object.

```java
Flight lax1 = new Flight();
Flight lax2 = new Flight();
// add passengers to both flights
Flight lax3 = null;
if(lax1.hasRoom(lax2))
    lax3 = lax1.createNewWithBoth(lax2);
//do some other work
if(lax != null)
    System,out.println("Flights combined");
```

## Access Modifiers

- Control class visibility
- Control member visibility
- Enable encapsulation

For classes, you can use either `public` or `default`.
For **attributes, methods and constructors**, you can use the one of the following:

| Modifier    | Description                                                                                |
| ----------- | ------------------------------------------------------------------------------------------ |
| `default`   | Accessible by classes in the same package. This is used when you don't specify a modifier. |
| `public`    | Accessible by any other class                                                              |
| `private`   | Accessible within the declared class                                                       |
| `protected` | Accessible in the same package and subclasses                                              |

## Field Accessors and Mutators

Field Encapsulation:

- The specific fileds a class uses to manage data value is generally considered to be an implementation detail
- In most cases these details should not be directlu accessible outside
- Use methods to control fileds access

Accessors and Mutators (get and set):

- Use accessor/mutator pattern to control field access

Accessor retrieves field values: also called `getter` (e.g. _getFieldName_)
Mutator modifies field values: also called `setter` (e.g. _setFieldName_)

```java
class Flight {
    private int seats; // private = restricted access
    //other members elided for clarity
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
}
```
