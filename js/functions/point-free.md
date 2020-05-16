# Point-free style

Point-free style means that the arguments of the function being defined are not explicitly mentioned, that the function is defined through function composition.

```js
foo(function(v) {
	return bar(v);
});
```

The point-free alternative would be not to talk about the argument v:

```js
foo(bar);
```

## Example 01

```js
function output(txt) {
	console.log(txt);
}
```
Some browsers require `console.log(..)` to run against the `console` context , so `x = console.log; x(..)` fails (because of lost `this` binding). Use `console.log.bind(console)` to be safe.

```js
var output = console.log.bind(console);
```

## Example 02

```js
function isShortEnough(str) {
	return str.length <= 5;
}

function isLongEnough(str) {
	return !isShortEnough(str);
}
```

`isLongEnough(..)` is the negation of `isShortEnough(..)`. So we can use an utility function here.

```js
function not(predicate) {
	return function negated(...args){
		return !predicate( ...args );
	};
}

var isLongEnough = not(isShortEnough);
```

## Example 03

```js
function printIf(predicate) {
	return function(msg) {
		if (predicate(msg)) {
			output(msg);
		}
	};
}
```

`printIf(..)` can be expressed in terms of a `when(..)` that looks like:

```js
function when(fn) {
	return function(predicate) { 	// in this case will be the function passed to printIt
		return function(...args) { 	// in this case the messages
			if (predicate(...args)) {
				return fn(...args);
			}
		};
	};
}
var printIf = when(output);

var msg1 = "Hello";
var msg2 = msg1 + " World";

printIf(isShortEnough)(msg1);	// Hello
printIf(isLongEnough)(msg2);  	// Hello World

```

The `when(..)` style is called currying. Currying is the process of breaking down a function into a series of functions that each take a single argument.


## Gotchas

Read more [here](https://dev.to/danhomola/point-free-gotchas-in-javascript--3pfi)
