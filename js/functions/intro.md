# Intro to Functions

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

Functions have a bonus proprety that make them different from objects, they can be invoked.

```js
function myFunction(a, b) {
  return a * b;
}
myFunction(10, 2); // invokation
```

The function we pass in is a [callback function](callbacks.md)
The outer function that takes in the function (our callback) is a [higher-order function](high-order-functions.md)