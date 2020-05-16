## Promises

Using two-pronged ‘facade’ functions that both:
- Initiate background web browser work and
- Return a placeholder object (promise) immediately in JavaScript

Promises allow us the synchronous way of writing code while giving us asynchronous execution of code. They are useful for async operations in a sync like manner. The most signficant ES6 feature.

Often used to [fecthing a JSON API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch) or doing some AJAX work. It's buit in right to the browser.

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

Another useful case for `Promise` is when you need some sort of flow control. This is an example where you would probably find out on a back end and maybe something like Node.js, when you are querying a database.

```js
const posts = [
    { title: 'I love JavaScript', author: 'Wes Bos', id: 1 },
    { title: 'CSS!', author: 'Chris Coyier', id: 2 },
    { title: 'Dev tools tricks', author: 'Addy Osmani', id: 3 },
  ];
  const authors = [
    { name: 'Wes Bos', twitter: '@wesbos', bio: 'Canadian Developer' },
    { name: 'Chris Coyier', twitter: '@chriscoyier', bio: 'CSS Tricks and CodePen' },
    { name: 'Addy Osmani', twitter: '@addyosmani', bio: 'Googler' },
  ];
  function getPostById(id) {
		// create a new promise
    return new Promise((resolve, reject) => {
      // using a settimeout to mimick a databse
      setTimeout(() => {
				// find the post we want
        const post = posts.find(post => post.id === id);
        if(post) {
          resolve(post);
        } else {
          reject(Error('Post not found!'));
        }
      },200);
    });
  }
  function hydrateAuthor(post) {
		// create a new Promise
    return new Promise((resolve, reject) => {
      setTimeout(() => {
				//finde the authour
        const authorDetails = authors.find(person => person.name === post.author);
        if(authorDetails) {
					// "hydrate" the post object with the author object
          post.author = authorDetails;
          resolve(post);
        } else {
          reject(Error('Author not Found!'));
        }
      }, 200);
    });
  }
  getPostById(1)
    .then(post => {
      return hydrateAuthor(post);
    })
    .then(author => {
      console.log(author);
    })
    .catch(err => {
      console.error(err);
    })
```


### Working with Multiple Promises

```js
const weather = new Promise((resolve) => {
	setTimeout(() => {
			resolve({temp: 29, conditions: 'Sunny with Clouds'});
	}, 2000);
});

const tweets = new Promise((resolve) => {
	setTimeout(() => {
			resolve(['I like cake', 'BBQ is good too!']);
	}, 500);
});

Promise
	.all([weather, tweets])
	.then(responses => {
			console.log(responses);
	});
```
Promises are special objects built into JavaScript that get returned immediately when we make a call to a web browser API/feature (e.g. fetch) that’s set up to return promises (not all are). They act as a placeholder for the data we expect to get back from the web browser feature’s background work. 

Any code we want to run on the returned data must also be saved on the promise object. Added using .then method to the hidden property ‘onFulfilment’. Promise objects will automatically trigger the attached function to run (with its input being the returned data).

But we need to know how our promise-deferred functionality gets back into JavaScript to be run. Example:

```js
function display(data){console.log(data)}
function printHello(){console.log("Hello");}
function blockFor300ms(){/* blocks js thread for 300ms */ }

setTimeout(printHello, 0);

const futureData = fetch('https://twitter.com/ticidesign/tweets/1')
futureData.then(display)

blockFor300ms()
console.log("Me first!");
```

**We have rules for the execution of our asynchronously delayed code**

Hold promise-deferred functions in a microtask queue and callback function in a task queue (Callback queue) when the Web Browser Feature (API) finishes.

Add the function to the Call stack (i.e. run the function) when:
- Call stack is empty & all global code run (Have the Event Loop check this condition)

Prioritize functions in the microtask queue over the Callback queue

## Promises, Web APIs, the Callback & Microtask Queues and Event loop enable:
**Non-blocking applications:** This means we don’t have to wait in the single thread and don’t block further code from running
**However long it takes:** We cannot predict when our Browser feature’s work will finish so we let JS handle automatically running the function on its completion
**Web applications:** Asynchronous JavaScript is the backbone of the modern web - letting us build fast ‘non-blocking’ applications

### Articles
[Jake Archibald wrote: Tasks, microtasks, queues and schedules](https://jakearchibald.com/2015/tasks-microtasks-queues-and-schedules/)