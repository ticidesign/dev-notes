# Dev Tools Tricks

## Regular
```js
console.log('hello');
```

## Interpolated
```js
console.log('Hello I am a %s string!', '💩');
```

## Styled
```js
console.log('%c I am some great text', 'font-size:50px; background:red; text-shadow: 10px 10px 0 blue')
```

## Warning!
```js
console.warn('OH NOOO');
```
## Error :|
```js
console.error('Shit!');
```
## Info
```js
console.info('Crocodiles eat 3-4 people per year');
```

## Testing
```js
const p = document.querySelector('p');
console.assert(p.classList.contains('ouch'), 'That is wrong!');
```

## Clearing
```js
console.clear();
```

## Viewing DOM Elements
```js
	console.log(p);
	console.dir(p);

	console.clear();
```

## Grouping together
```js
	dogs.forEach(dog => {
		console.groupCollapsed(`${dog.name}`);
		console.log(`This is ${dog.name}`);
		console.log(`${dog.name} is ${dog.age} years old`);
		console.log(`${dog.name} is ${dog.age * 7} dog years old`);
		console.groupEnd(`${dog.name}`);
	});
```
## Counting
```js
console.count('Wes');
console.count('Wes');
console.count('Steve');
console.count('Steve');
console.count('Wes');
console.count('Steve');
console.count('Wes');
console.count('Steve');
console.count('Steve');
console.count('Steve');
console.count('Steve');
console.count('Steve');
```

## Timing
```js
console.time('fetching data');
fetch('https://api.github.com/users/ticidesign')
	.then(data => data.json())
	.then(data => {
		console.timeEnd('fetching data');
		console.log(data);
	});

console.table(dogs);
```
