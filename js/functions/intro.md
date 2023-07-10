# Intro to Functions

Every function is a single-valued collection of pairs.

#### Characteristics:

**Total:** for every input there is a corresponding output

**Deterministic:** Always receive the same output for a given input

**No Side Effects:** No observable effects besides computing a value

Functions in JavaScript are considered first-class objects. They behave as an object.
They can co-exist with and can be treated like any other javascript object.

1. assigned to variables and properties of other objects (method)

2. passed as arguments into functions

```js
function copyArrayAndManipulate(array, instructions) {
    let output = [];
    for (let i = 0; i < array.length; i++) {
        output.push(instructions(array[i]));
    }
    return output;
}
function multiplyBy2(input) {
    return input * 2;
}
let result = copyArrayAndManipulate([1, 2, 3], multiplyBy2);
```

3. returned as values from functions (closure)

Functions have a bonus property that make them different from objects, they can be invoked.

```js
function myFunction(a, b) {
  return a * b;
}
myFunction(10, 2); // invokation
```

The function we pass in is a [callback function](callbacks.md)
The outer function that takes in the function (our callback) is a [higher-order function](high-order-functions.md)

## Generalized Functions

‘Parameters’ (placeholders) mean we don’t need to decide what data to run our functionality on until we run the function

- Then provide an actual value (‘argument’) when we run the function Higher order functions follow this same principle.
- We may not want to decide exactly what some of our functionality is until we run our function

## Callbacks & Higher Order Functions

One of the most misunderstood concepts in JavaScript

- Enables powerful pro-level functions like map, filter, reduce (a core aspect of functional programming)
- Makes our code more declarative and readable
- Forms the backbone of technical interview (and professional mid/senior level engineering interviews)
