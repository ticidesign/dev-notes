# Arrow Functions

Introducing arrow functions - a shorthand way to save functions

```js
function multiplyBy2(input) { return input * 2; }
const multiplyBy2 = (input) => { return input*2 }
const multiplyBy2 = (input) => input*2
const multiplyBy2 = input => input*2
const output = multiplyBy2(3) //6
```

Anonymous and arrow functions

- Improve immediate legibility of the code
- But at least for our purposes here they are ‘syntactic sugar’ - we’ll see their full effects later
- Understanding how they’re working under-the-hood is vital to avoid confusion

```js

function copyArrayAndManipulate(array, instructions) {
 const output = [];
    for (let i = 0; i < array.length; i++) {
        output.push(instructions(array[i]));
    }
    return output;
}
const multiplyBy2 = input => input*2
const result = copyArrayAndManipulate([1, 2, 3], multiplyBy2);
// const result = copyArrayAndManipulate([1, 2, 3], input => input*2);

```