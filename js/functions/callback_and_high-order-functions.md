# Callback & Higher-order functions

One of the most misunderstood concepts in JavaScript
- Enables powerful pro-level functions like map, filter, reduce (a core aspect of functional programming)
- Makes our code more declarative and readable
- Forms the backbone of technical interview (and professional mid/senior level engineering interviews)

Which is our Higher Order Function? Which is our Callback Function?

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

The outer function that takes in a function is our higher-order function (`copyArrayAndManipulate`)
The function we insert is our callback function (`multiplyBy2`)


## Higher-order functions
Takes in a function or passes out a function
Just a term to describe these functions - any function that does it we call that - but there's nothing different about them inherently

Benefits

- Simplify the code
- Keep code DRY
- Allow the running of asynchronous code

Callbacks and Higher Order Functions simplify our code and keep it DRY

**Declarative readable code**: Map, filter, reduce - the most readable way to write code to work with data
**Pro interview prep**: One of the most popular topics to test in interview both for Codesmith and mid/senior level job interviews
**Asynchronous JavaScript**: Callbacks are a core aspect of async JavaScript, and are under-the-hood of promises, async/await

Exercise [http://csbin.io/callbacks](http://csbin.io/callbacks)