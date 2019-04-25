# Iterators

In JavaScript an *iterator* is an object which defines a sequence and potentially a return value upon its termination. More specifically an iterator is any object which implements the Iterator protocol by having a next() method which returns an object with two properties: value, the next value in the sequence; and done, which is true if the last value in the sequence has already been consumed. If value is present alongside done, it is the iterator's return value.

Iterators and Generators bring the concept of iteration directly into the core language and provide a mechanism for customizing the behavior of `for...of` loops.

```js
const str = "Hello";
const world = ["W", "o", "r", "l", "d"];

const it1 = str[Symbol.iterator]();
const it2 = world[Symbol.iterator]();

it1.next();			// { value: "H", done: false }
it1.next();			// { value: "e", done: false }
it1.next();			// { value: "l", done: false }
it1.next();			// { value: "l", done: false }
it1.next();			// { value: "o", done: false }
it1.next();			// { value: undefined, done: true }

it2.next();			// { value: "W", done: false }
//...

```

## Imperative Iterator ( old way )

```js
const str = "Hello";

for(
	let it = str[Symbol.iterator](), v, result;
	(result = it.next()) && result.done && (v = result.value || true);
) {
	console.log(v);
} // "H" "e" "l" "l" "o"
```

## Declarative Iterator

```js
const str = "Hello";
let it = str[Symbol.iterator]();

for(let v of it) {
	console.log(v);
} // "H" "e" "l" "l" "o"

for(let v of str) {
	console.log(v);
} // "H" "e" "l" "l" "o"

const letter = [...str]; 😍
letter; // ["H", "e", "l", "l", "o"]
```

## Data structure without iterators

### Objects are not iterables

```js
const obj = {
	a: 1,
	b: 2,
	c: 3
};

for(let v of obj) {
	console.log(v);
} // TypeError!
```

Use a generator in this case

```js
const obj = {
	a: 1,
	b: 2,
	c: 3,
	*[Symbol.iterator](){
		for(let key of Object.key(this)){ // Object.key return an iterable array
			yield this[key];
		}
	}
};
[...obj]; //[1, 2, 3]
```
