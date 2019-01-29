# !! Operator

## What is the !! (not not) operator?

*Remember it by "bang, bang you're boolean"*


It’s all about “type coercion”, which is fancy words for converting a value from one type to another.
A horribly obscure way to do a type conversion.
`!` is NOT.
So `!true` is `false`, and `!false` is `true`. `!0` is `true`, and `!1` is `false`.

So you're converting a value to a boolean, then inverting it, then inverting it again.

```js
> const x = { a: 1 }
undefined
> x
{ a: 1 }
> !x
false
> !!x
true
```

More examples:

```js
			!!false === false
			!!true === true

			!!0 === false
			!!parseInt("foo") === false // NaN is falsy
			!!1 === true
			!!-1 === true  // -1 is truthy

			!!"" === false // empty string is falsy
			!!"foo" === true  // non-empty string is truthy
			!!"false" === true  // ...even if it contains a falsy value

			!!new Boolean(false)  // true
			!!Boolean(false) // false

			!!window.foo === false // undefined is falsy
			!!null === false // null is falsy

			!!{} === true  // an (empty) object is truthy
			!![] === true  // an (empty) array is truthy; PHP programmers beware!
```

You could write `Boolean(req.session.keystoneItemId)` instead which is more idiot proof.
