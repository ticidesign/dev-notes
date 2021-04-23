# Classes

- Java is an object-oriented programming language.
- Objects encapsulate data, operations and usage semantics
- Allow storage and manupulation details to be hidden
- Separates what is to be done from how it is done.

A class is a template to create objects.

A class is made of both state and executable code.

- **Fields**: store object state
- **Methods**: excutable code that manipulates state and performs operations
- **Constructors**: executable code used during object creation to set initial state

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
