## unary

A `unary` function is a function that takes one argument.

```js
function increment(x) {
	return sum(x,1)
}
```

## binary

A `binary` function is a function that takes two inputs.

```js
function sum(x,y) {
	return x + y;
}
```

## variadic

A variadic function is a function where the total number of parameters are unknown and can be adjusted at the time the method is called.

```js
function f(...args) {
	console.log(args);
}
```
