# Helper Function - Once

Every so often you have a function which you only want to run once.  Oftentimes these functions are in the form of event listeners which may be difficult to manage.  Of course if they were easy to manage, you'd just remove the listeners but that's a perfect world and sometimes you simply want the ability to only allow a function to be called once.  Here's the JavaScript function to make that possible!

Think of this once function as a wrapper for the function you provide:
```js
function once(fn, context) { 
	var result;

	return function() { 
		if(fn) {
			result = fn.apply(context || this, arguments);
			fn = null;
		}

		return result;
	};
}

// Usage
var canOnlyFireOnce = once(function() {
	console.log('Fired!');
});

canOnlyFireOnce(); // "Fired!"
canOnlyFireOnce(); // nada
```

The wrapping function is fired only once because a tracker variable is used to ensure the function is only executed once.  Many JavaScript toolkits offer this as a feature but the code to accomplish this feat is so small that it's good to have available in the case that you can dodge a JavaScript toolkit!

For anything you don’t want to be able to be executed more than once. Mostly for initialization stuff.