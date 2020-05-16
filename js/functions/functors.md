# Functors

A functor is an object that has a map method. Arrays in JavaScript implement map and are therefore functors. Promises, Streams and Trees often implement map in functional languages, and when they do, they are considered functors. The map method of the functor takes it’s own contents and transforms each of them using the transformation callback passed to map, and returns a new functor, which contains the structure as the first functor, but with the transformed values.

What qualifies as a functor:
1. Transformation of contents
2. Maintain structure
3. Returns a new functor

## Example

Creating a identity functor and refactor using dot chaining:

```js
const nextCharForNumberString = str => {
  const trimmed = str.trim();
  const number = parseInt(trimmed);
  const nextNumber = number + 1;
  return String.fromCharCode(nextNumber);
};

const result = nextCharForNumberString("  64 ");

console.log(result);
```

```js
const Box = x => ({
  map: f => Box(f(x)),
  fold: f => f(x),
  inspect: `Box(${x})`,
})

const nextCharForNumberString = (str) =>
  Box(str)
  .map(x => x.trim())
  .map(trimmed => parseInt(trimmed))
  .map(number => number + 1)
  .fold(String.fromCharCode)
;
const result = nextCharForNumberString("  64 ");

console.log(result);
```

Another example:

```js
const first = xs => xs[0];

const halfTheFirstLargeNumber = xs => {
  const found = xs.filter(x => x >= 20);
  const answer = first(found) / 2;
  return `The answer is ${answer}`;
};

const res = halfTheFirstLargeNumber([1, 4, 50]);
console.log(res);
```

```js
const Box = x => ({
  map: f => Box(f(x)),
  fold: f => f(x),
  inspect: `Box(${x})`,
})

const first = xs => xs[0];

const halfTheFirstLargeNumber = xs => 
 Box(xs)
  .map(xs => xs.filter(x => x >= 20))
  .map(found => first(found) / 2)
  .fold(answer => `The answer is ${answer}`)
;

const res = halfTheFirstLargeNumber([1, 4, 50]);
console.log(res);
```


## Articles

[Functors Categories](https://medium.com/javascript-scene/functors-categories-61e031bac53f)

[Functional Programming: What is a functor?](https://www.quora.com/Functional-Programming/Functional-Programming-What-is-a-functor/answer/Tikhon-Jelvis?share=1)