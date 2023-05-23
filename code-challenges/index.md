# CHALLENGES!

Solutions [https://codesandbox.io/s/parseurl-7pbcz8?file=/src/App.tsx](https://codesandbox.io/s/parseurl-7pbcz8?file=/src/App.tsx)

## parseUrl

Implement parseUrl() manually without any native URL parsing APIs or libraries.

```js
/**
 * CHALLENGES!
 * Implement parseUrl() manually without any native URL parsing APIs or libraries
 * GO!
 */

function parseUrl() {}

console.log(parseUrl("http://example.com?a=1&b=2&a=2")); // should log http://example.com?a=1&b=2
console.log(parseUrl("http://example.com?a=1&b=2", ["b"])); // should log http://example.com?a=1
console.log(parseUrl("http://example.com?a=1&b=2", ["a", "b"])); // should log http://example.com
```

## reverse string

Create a function to reverse a string

```js
/*
    reverse('Hello, world!'); // !dlrow ,olleH
*/
```

## flatten

Without using .flat(), create a function to flatten an array

```js
/**
 * const exampleArray = [1,2,[3,4, [5,6,7], 8], 9, 10];
 * flatten(exampleArray); // [1,2,3,4,5,6,7,8,9,10]
 *
 */
```

## duplicates strings

Create a function that takes a string and returns a
new string with duplicates removed

```js
/*
 const str = 'This is is a test test string';
 removeDuplicates(str); // 'This is a test string'
 */
```

## bind

Implement Function.prototype.bind()

```js
/*
    const foo = function() {
        console.log(this.bar);
    }

    let baz = foo.bind({bar: 'hello'})

    baz(); // Hello
*/
```

## debounce

In JavaScript, throttle and debounce are two techniques used to optimize the execution of functions that are triggered by events such as scroll events or user input. Both throttle and debounce help improve performance by controlling how often a function is invoked in response to these events.

Debounce: Debouncing groups multiple function calls into a single execution. It ensures that the function is only invoked after a specified delay since the last trigger event. If the function is triggered multiple times within the delay period, the previous pending invocation is canceled, and a new invocation is scheduled. Debouncing is beneficial when you want to postpone the execution of a function until a user has finished an action, such as typing in an input field or resizing a window.

Implement debounce with `setInterval()` or `setTimeOut()`

[leetcode.com/problems/debounce/](https://leetcode.com/problems/debounce/)
