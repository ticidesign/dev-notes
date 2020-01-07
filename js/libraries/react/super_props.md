# Why do we write super(props)?

[from Dan Abramov's blog post](https://overreacted.io/why-do-we-write-super-props/)

## Why do we call super?

In JavaScript, `super` refers to the parent class constructor. You can’t use `this` in a constructor until after you’ve called the parent constructor.

```js
class Checkbox extends React.Component {
  constructor(props) {
    // 🔴 Can’t use `this` yet
    super(props);
    // ✅ Now it’s okay though
    this.state = { isOn: true };
  }
  // ...
}
```

There’s a good reason for why JavaScript enforces that parent constructor runs before you touch `this`. Consider a class hierarchy:

```js
class Person {
  constructor(name) {
    this.name = name;
  }
}

class PolitePerson extends Person {
  constructor(name) {
    this.greetColleagues(); // 🔴 This is disallowed, read below why
    super(name);
  }
  greetColleagues() {
    alert('Good morning folks!');
		alert('My name is ' + this.name + ', nice to meet you!'); // 🔴  this.name isn’t even defined yet
  }
}
```
To avoid such pitfalls, JavaScript enforces that if you want to use `this` in a constructor, you have to call `super` first. Let the parent do its thing! And this limitation applies to React components defined as classes too.

## Why pass props?

Even if you call `super()` without the `props` argument, you’ll still be able to access `this.props` in the render and other methods. It turns out that React also assigns props on the instance right after calling your constructor:

```js
// Inside React
  const instance = new YourComponent(props);
  instance.props = props;
```

So even if you forget to pass `props` to `super()`, React would still set them right afterwards.

React would later assign `this.props` after your constructor has run. But `this.props` would still be undefined between the super call and the end of your constructor:

```js
// Inside React
class Component {
  constructor(props) {
    this.props = props;
    // ...
  }
}

// Inside your code
class Button extends React.Component {
  constructor(props) {
    super(); // 😬 We forgot to pass props
    console.log(props);      // ✅ {}
    console.log(this.props); // 😬 undefined
  }
  // ...
}
```
Always passing down `super(props)`, even though it isn’t strictly necessary. This ensures `this.props` is set even before the constructor exits.

```js
class Button extends React.Component {
  constructor(props) {
    super(props); // ✅ We passed props
    console.log(props);      // ✅ {}
    console.log(this.props); // ✅ {}
  }
  // ...
}
```
Now, with Hooks, we don’t even have `super` or `this`.
