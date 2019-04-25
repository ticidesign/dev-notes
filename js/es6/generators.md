# Generators

Generators compute their yielded values on demand, which allows them to efficiently represent sequences that are expensive to compute, or even infinite sequences as demonstrated above.

The `next()` method also accepts a value which can be used to modify the internal state of the generator. A value passed to `next()` will be treated as the result of the last yield expression that paused the generator.

```js
function *main() {
	yield 1;
	yield 2;
	yield 3;
	return 4;
}

const it = main();

it1.next();			// { value: "1", done: false }
it1.next();			// { value: "2", done: false }
it1.next();			// { value: "3", done: false }
it1.next();			// { value: "4", done: true }

[...main()]			//[1, 2, 3]
```
