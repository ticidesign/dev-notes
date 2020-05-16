# Objects

Objects - store functions with their associated data! This is the principle of encapsulation - and it’s going to transform how we can ‘reason about’ our code.

Examples:

Imagine I have to store each user in my app with their respective data: 

user1:
- name: ‘Tim’
- score: 3

user2:
- name: ‘Stephanie’
- score: 5

And the functionality I need to have for each user is a increment score functionality.

How could I store my data and functionality together in one place?

### Solution 1: Generate objects using a function**

```js
function userCreator(name, score) {
    const newUser = {};
    newUser.name = name;
    newUser.score = score;
    newUser.increment = function() {
        newUser.score++;
    };
    return newUser;
};
const user1 = userCreator("Tim", 3);
const user2 = userCreator("Stephanie", 5);
user1.increment()
```

**Problems:** Each time we create a new user we make space in our computer's memory for all our data and functions. But our functions are just copies Is there a better way?

**Benefits:** It's simple and easy to reason about!

### Solution 2: Using the prototype chain

Store the increment function in just one object and have the interpreter, if it doesn't find the function on user1, look up to that object to check if it's there. 

Link user1 and functionStore so the interpreter, on not finding .increment, makes sure to check up in functionStore where it would find it.

Make the link with [Object.create()](./object_create.md) technique

```js
function userCreator (name, score) {
    const newUser = Object.create(userFunctionStore);
    newUser.name = name;
    newUser.score = score;
    return newUser;
};
const userFunctionStore = {
    increment: function(){this.score++;},
    login: function(){console.log("Logged in");}
};
const user1 = userCreator("Tim", 3);
const user2 = userCreator("Stephanie", 5);
user1.increment();
```

All objects have a `__proto__` property by default which defaults to linking to a big object - Object.prototype full of (somewhat) useful functions.

We get access to it via userFunctionStore’s `__proto__` property - the chain

```js
user1.hasOwnProperty('score') //true
```

**Problems:** No problems! It's beautiful. Maybe a little long-winded

Write this every single time - but it's 6 words!

**Benefits:** Super sophisticated but not standard

### Solution 3:  Introducing the keyword that automates the hard work: new

When we call the function that returns an object with [`new`](./new_keyword.md) in front we automate 2 things:

1. Create a new user object
2. Return the new user object

The new keyword automates a lot of our manual work:

```js
function userCreator(name, score){
    this.name = name;
    this.score = score;
}
userCreator.prototype.increment = function(){ this.score++; };
userCreator.prototype.login = function(){ console.log("login"); };

const user1 = new userCreator("Tim", 3)
user1.increment()
```

**Benefits:**

Faster to write. Often used in practice in professional code

**Problems:**

95% of developers have no idea how it works and therefore fail interviews
We have to upper case first letter of the function so we know it requires ‘new’ to work!

### Solution 4: The class ‘syntactic sugar’

We’re writing our shared methods separately from our object ‘constructor’ itself (off in the userCreator.prototype object)

Other languages let us do this all in one place. ES2015 lets us do so too

```js
class UserCreator {
  constructor (name, score){
    this.name = name;
    this.score = score;
  }
  increment (){ this.score++; }
  login (){ console.log("login"); }
}

const user1 = new UserCreator("Stephanie", 5);
user1.increment();
```

**Benefits:** Emerging as a new standard and it feels more like style of other languages (e.g. Python)

**Problems:**  99% of developers have no idea how it works and therefore fail interviews.