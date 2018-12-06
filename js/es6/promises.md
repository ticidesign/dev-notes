## Promises

Promises allow us the synchronous way of writing code while giving us asynchronous execution of code.
They are useful for async operations in a sync like manner.

Often used to [fecthing a JSON API]9https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch) or doing some AJAX work. It's buit in right to the browser.

[Promises](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise) is something that will happen between now a in the end of time. Something that will happen in the future, but not immediately.

```js
const starshipsPromise = fetch('https://swapi.co/api/starships/'); // queue it up
console.log('Done!');
console.log(starshipsPromise);

starshipsPromise
	.then(data => return data.json())
	.then(data => console.log(data))
	.catch(error => console.error(error)) // catch any error along the way

```

### Build our own Promise

In essence, a Promise in JavaScript is a lot like a promise made in real life. As promises in real life are either kept or broken, JavaScript Promises get either resolved or rejected.

To explore this further, let’s take a look at how a small child promising his parents to clean his room looks like in JavaScript.


```js
let promiseToCleanTheRoom = new Promise((resolve, reject) => {
	let isClean = true;
	if(isClean){
		resolve('clean');
	} else {
		reject(Error('not clean')); // throw an new Error to be able to see where the error come from.
	}
});
```

We can now execute our promiseToCleanTheRoom function by writing out the following.

```js
promiseToCleanTheRoom
	.then(result => console.log(`the room is ${result}`))
	.catch(result => console.log(`the room is ${result}`))
```
Once the `promiseToCleanTheRoom` gets executed, our `then` function will trigger only if the Promise gets resolved.
Likewise, our `catch` function will only trigger only if the Promise gets rejected.



### Chaining Promise + Flow Control
