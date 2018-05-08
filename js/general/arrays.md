## Small notes and shortcuts for handling arrays



#### Counting the occurrences / frequency of array elements with ES6
```js
const a = [5, 5, 5, 2, 2, 2, 2, 2, 9, 4];

const aCount = new Map([...new Set(a)].map(
    x => [x, a.filter(y => y === x).length]
));
```
```js
aCount.get(5)  // 3
aCount.get(2)  // 5
aCount.get(9)  // 1
aCount.get(4)  // 1
```

This example passes the input array to the `Set` constructor creating a collection of **unique** values. The [spread syntax](https://developer.mozilla.org/en/docs/Web/JavaScript/Reference/Operators/Spread_operator) then expands these values into a new array so we can call `map` and translate this into a two-dimensional array of `[value, count]` pairs - i.e. the following structure:

```js
Array [
   [5, 3],
   [2, 5],
   [9, 1],
   [4, 1]
]
```
The new array is then passed to the `Map` constructor resulting in an [iterable](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Iteration_protocols) object:

```js
Map {
    5 => 3,
    2 => 5,
    9 => 1,
    4 => 1
}
```
The great thing about a `Map` object is that it preserves data-types - that is to say `aCount.get(5)` will return `3` but `aCount.get("5")` will return `undefined`. It also allows for **any** value / type to act as a key meaning this solution will also work with an array of objects.



#### Change all values in an array to a specific value
```js
const array = [1, 2, 3, 4];

array.fill(4) // array = [4, 4, 4, 4]
```