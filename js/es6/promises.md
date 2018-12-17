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
