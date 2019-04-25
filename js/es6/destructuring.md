# Destructuring

The two most used data structures in JavaScript are `Object` and `Array`.

Destructuring assignment is a special syntax that allows us to “unpack” arrays or objects into a bunch of variables, as sometimes they are more convenient. Destructuring also works great with complex functions that have a lot of parameters, default values, and soon we’ll see how these are handled too.


## Array

```js
// we have an array with the firstName and lastName
let arr = ["Ticiana", "Andrade"]

// destructuring assignment
let [firstName, lastName] = arr;

console.log(firstName); // Ticiana
console.log(lastName); // Andrade
```

## Object

```js
// Object
var o = {
	property: value
	source: target
}
// Destructuring
var {
	property: value
	target: source
} = o;

```

```js
let options = {
  title: "Menu",
  width: 100,
  height: 200
};

let {title, width, height} = options;

console.log(title);  // Menu
console.log(width);  // 100
console.log(height); // 200
```

```js
var { a: first, b: second, ...third } = { a: 1, b: 2, c: 3, d: 4 };

console.log(first) // 1;
console.log(second) // 2;
console.log(third) // {c: 3, d: 4};
```

## Named arguments

```js
function myFunction({
  text = "",
  line = 0,
  truncate = 100
} = {}) {
 // Even if the passed in object is missing a given key, the default is used!
}
```


## Destructing and Restructuring

```js
function handleResponse({
	/* destructuring here */
	topic: "JavaScript",
	format: "Live",
	slides: {
		start: 0,
		end: 100
	}
} = {}) {
	TestCase({
		/* restructuring here */
		topic,
		format,
		slides: {
			start,
			end
		}
	});
}

function TestCase(data) {
	console.log(
		data.topic == "JavaScript" &&
		data.format == "Live" &&
		data.slides.start === 0 &&
		data.slides.end == 100
	);
}
```
