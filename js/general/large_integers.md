## Working with large integers in JavaScript

### JavaScript only supports 53 bit integers

All numbers in JavaScript are [floating point](floating_numbers.md) which means that integers are always represented as

	sign × mantissa × 2<sup>exponent</sup>

The mantissa has 53 bits. You can use the exponent to get higher integers, but then they won’t be contiguous, any more. For example, you generally need to multiply the mantissa by two (exponent 1) in order to reach the 54th bit. However, if you multiply by two, you will only be able to represent every second integer:

```js
> Math.pow(2, 53)  // 54 bits
9007199254740992
> Math.pow(2, 53) + 1
9007199254740992
> Math.pow(2, 53) + 2
9007199254740994
> Math.pow(2, 53) + 3
9007199254740996
> Math.pow(2, 53) + 4
9007199254740996
```

<small> * The Math.pow() function returns the base to the exponent power, that is, baseexponent.</small>

Rounding effects during the addition make things unpredictable for odd increments (+1 versus +3). The actual representation is a bit more complicated [1], but this explanation should help you understand the basic problem.

### Working with large integers in JavaScript

[more here](http://2ality.com/2012/07/large-integers.html)
